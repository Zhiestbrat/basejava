package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serializer.StreamSerializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author p.kondakov
 */
public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final StreamSerializer streamSerializer;

    protected PathStorage(String dir, StreamSerializer streamSerializer) {
        this.directory = Paths.get(dir);
        this.streamSerializer = streamSerializer;
        Objects.requireNonNull(directory, "directory must not be null");
        Objects.requireNonNull(streamSerializer, "streamSerializer must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not directory or is not writable");
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
       return getFilesStream().map(this::doGet).collect(Collectors.toList());
    }

    @Override
    protected void doUpdate(Resume resume, Path path) {
        try {
            streamSerializer.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", path.toFile().getName(), e);
        }
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return streamSerializer.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (Exception e) {
            throw new StorageException("Path read error", path.toFile().getName(), e);
        }
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't creat Path ", path.toFile().getName(), e);
        }
        doUpdate(resume, path);
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't delete Path ", path.toFile().getName(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    public void clear() {
        getFilesStream().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getFilesStream().count();
    }

    private Stream<Path> getFilesStream() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", null);
        }
    }
}

package io.kuo.tahoe.service;

import io.kuo.tahoe.entity.Task;
import io.kuo.tahoe.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by nikog on 5/3/2015.
 */
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long taskId) {
        return taskRepository.findOne(taskId);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void deleteById(Long taskId) {
        taskRepository.delete(taskId);
    }
}

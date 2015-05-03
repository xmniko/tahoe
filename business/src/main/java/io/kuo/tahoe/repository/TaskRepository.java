package io.kuo.tahoe.repository;

import io.kuo.tahoe.entity.Task;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by nikog on 5/3/2015.
 */
public interface TaskRepository extends CrudRepository<Task, Long> {
}

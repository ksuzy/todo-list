package de.ksuzy.todolist.database.repository;

import de.ksuzy.todolist.database.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    /**
     * Retrieves all tasks that are not marked as done.
     * Useful for displaying pending tasks to the user.
     */
    List<Task> findByDoneFalse();

    /**
     * Retrieves all tasks that are marked as done.
     * Can be used to show completed tasks in a separate list.
     */
    List<Task> findByDoneTrue();

    /**
     * Retrieves all tasks with a deadline earlier than the given timestamp.
     * Typically used to find overdue tasks.
     *
     * @param time The timestamp to compare against.
     * @return List of overdue tasks
     */
    List<Task> findByDeadlineBefore(LocalDateTime time);

    /**
     * Retrieves all tasks that do not have a deadline assigned.
     * Useful for identifying open-ended or flexible tasks.
     */
    List<Task> findByDeadlineIsNull();

    /**
    * Searches for tasks with a title that contains the given keyword, ignoring case.
    * Useful for implementing a search feature.
    * @param keyword The keyword to search for in task titles.
    */
    List<Task> findByTitleContainingIgnoreCase(String keyword);

    /**
     * Retrieves all tasks that are not completed and have a deadline before the given timestamp.
     * This query helps to identify unfinished overdue tasks.
     *
     * @param deadline The deadline to compare against.
     */
    @Query("SELECT t FROM Task t WHERE t.done = false AND t.deadline < :deadline")
    List<Task> findOverdueTasks(@Param("deadline") LocalDateTime deadline);
}

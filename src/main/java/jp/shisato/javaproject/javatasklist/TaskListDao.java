package jp.shisato.javaproject.javatasklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskListDao {
    private final JdbcTemplate jdbcTemplate;
    record TaskItem(String id, String task, String deadline, boolean done) {}

    @Autowired
    TaskListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(TaskItem taskItem) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(taskItem);

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("tasklist");

        insert.execute(param);
    }

    public List<TaskItem> findAll() {
        String query = "SELECT * FROM tasklist";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
        List<TaskItem> taskItems = result.stream()
                .map((Map<String, Object> row) -> new TaskItem(
                        row.get("id").toString(),
                        row.get("task").toString(),
                        row.get("deadline").toString(),
                        (Boolean)row.get("done")
                )).toList();

        return taskItems;
    }

    public String getId(int index) {
        List<TaskItem> taskItems = findAll();

        if (taskItems.size() <= 0) { return "-1"; }

        return taskItems.get(index).id().toString();
    }

    public int delete(String id) {
        int number = jdbcTemplate.update("DELETE FROM tasklist WHERE id = ?", id);

        return number;
    }

    public int update(TaskItem taskItem) {
        int number = jdbcTemplate.update(
                "UPDATE tasklist SET task = ?, deadline = ?, done = ? WHERE id = ?",
                taskItem.task(),
                taskItem.deadline(),
                taskItem.done(),
                taskItem.id()
        );

        return number;
    }
}

package com.techelevator.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class HomeworkJDBC implements HomeworkDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public HomeworkJDBC(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Homework getAllHomeworkByTeacherId(int teacherId) {
		SqlRowSet result = jdbcTemplate
				.queryForRowSet(
						"SELECT * FROM homework" + " JOIN curriculum ON homework.course_id = curriculum.course_id"
								+ " JOIN homework_submission ON curriculum.id = homework_submission.curriculum_id"
								+ " JOIN student ON homework_submission.student_id = student.id"
								+ " JOIN app_user ON student.appuser_id = app_user.id"
								+ " JOIN teacher ON app_user.id = teacher.appuser_id" + " WHERE teacher.id = ?",
						teacherId);
		if (result.next()) {
			return mapRowSetToHomework(result);
		}
		return null;
	}

	@Override
	public Homework getHomeworkByStudentId(int studentId) {
		SqlRowSet result = jdbcTemplate
				.queryForRowSet(
						"SELECT * FROM homework" + " JOIN curriculum ON homework.course_id = curriculum.course_id"
								+ " JOIN homework_submission ON curriculum.id = homework_submission.curriculum_id"
								+ " JOIN student ON homework_submission.student_id = student.id"
								+ " JOIN app_user ON student.appuser_id = app_user.id" + " WHERE student.id = ?",
						studentId);
		if (result.next()) {
			return mapRowSetToHomework(result);
		}
		return null;
	}

	public Homework getHomeworkByCurriculumId(int curriculumId) {
		SqlRowSet result = jdbcTemplate.queryForRowSet(
				"SELECT * FROM homework" + " JOIN curriculum ON homework.course_id = curriculum.course_id"
						+ " JOIN homework_submission ON curriculum.id = homework_submission.curriculum_id"
						+ " WHERE curriculum.id = ?",
				curriculumId);
		if (result.next()) {
			return mapRowSetToHomework(result);
		}
		return null;
	}

	@Override
	public List<Homework> getHomeworkStatus(int id) {
		String sqlRequestHomeworkByAppUserId = "SELECT * FROM homework WHERE id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlRequestHomeworkByAppUserId, id);
		List<Homework> homeworkStatus = new ArrayList<Homework>();
		while (result.next()) {
			Homework homeworkResults = mapRowSetToHomework(result);
			homeworkStatus.add(homeworkResults);
		}
		return homeworkStatus;
	}
	
	
	@Override
	public void updateHomeworkByHomeworkId(int homeworkId, boolean complete) {
		jdbcTemplate.update("UPDATE homework SET complete = ? WHERE id = ?", complete, homeworkId);

		}
	
	
	
	
	//
	// @Override
	// public Homework getIncompleteHomework(boolean complete, int appuserId) {
	// SqlRowSet result = jdbcTemplate.queryForRowSet("SELECT * FROM homework"
	// + "JOIN curriculum ON homework.course_id = curriculum.course_id"
	// + "JOIN homework_submission ON curriculum.id =
	// homework_submission.curriculum_id"
	// + "JOIN student ON homework_submission.student_id = student.id"
	// + "JOIN app_user ON student.appuser_id = app_user.id" + "WHERE complete =
	// false AND appuser_id = ?",
	// appuserId);
	// if (result.next()) {
	// return mapRowSetToHomework(result);
	// }
	// return null;
	// }

	@Override
	public int getProgressPercentageByHomeworkId(int homeworkId, boolean complete) {
		int progressPercentage = 0;
		while (homeworkId > 0) {
			if (complete = true) {
				progressPercentage = 100;
			} else if (complete = false) {
				progressPercentage = 0;
			}
			return progressPercentage;
		}
		return progressPercentage;
	}

	public void createHomework(Homework homework) {
		String query = "INSERT INTO homework Values('" + homework.getHomeworkName() + "','" + homework.getDueDate()
				+ "','" + homework.isComplete() + "','" + homework.getQuestionId() + "','" + homework.getAnswerId()
				+ "','" + homework.getCourseId() + "')";
		jdbcTemplate.update(query);
	}

	public void updateHomeworkByStudentSubmission(Homework homework) {

	}

	@Override
	public List<Homework> getAllHomework() {
		List<Homework> allHomework = new ArrayList<>();
		String sqlSelectAllHomework = "SELECT * FROM homework ORDER BY name ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllHomework);
		while (results.next()) {
			Homework homework = mapRowSetToHomework(results);
			allHomework.add(homework);
		}
		return allHomework;
	}
	
	@Override
	public void updateHomeworkByUserId(int appuserId) {

		SqlRowSet result = jdbcTemplate.queryForRowSet("UPDATE homework SET complete = TRUE WHERE appuser_id = ?",
				appuserId);
	}

	

	private Homework mapRowSetToHomework(SqlRowSet results) {
		Homework homework = new Homework();
		homework.setHomeworkId(results.getInt("id"));
		homework.setHomeworkName(results.getString("name"));
		homework.setDueDate(results.getDate("due_date"));
		homework.setComplete(results.getBoolean("complete"));
		homework.setQuestionId(results.getInt("question_id"));
		homework.setAnswerId(results.getInt("answer_id"));
		homework.setCourseId(results.getInt("course_id"));
		return homework;
	}

}
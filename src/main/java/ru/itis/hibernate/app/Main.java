package ru.itis.hibernate.app;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.itis.hibernate.models.Course;
import ru.itis.hibernate.models.Lesson;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate/hibernate.cfg.xml");

        // на основе hiberate.cfg.xml соберёт фабрику сессий, т.е. соединений
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // открылась сессия
        Session session = sessionFactory.openSession();

        // создали курс
        Course java = Course.builder()
                .title("Java")
                .build();

        // создали урок
        Lesson sqlLesson = Lesson.builder()
                .course(java)
                .name("Урок по SQL")
                .build();

        Lesson springLesson = Lesson.builder()
                .course(java)
                .name("Урок по spring")
                .build();

        Lesson hibernateLesson = Lesson.builder()
                .course(java)
                .name("Урок по hibernate")
                .build();


        // сохраняем
        session.save(java);
        session.save(sqlLesson);
        session.save(springLesson);
        session.save(hibernateLesson);

        // закрылась сессия
        session.close();

        // заново переоткроем сессию
        session = sessionFactory.openSession();

        // Course.class - придёт объект типа Course
        Query<Course> courseQuery =  session.createQuery("from Course course where course.title = 'Java'", Course.class);
        Course courseFromDb = courseQuery.getSingleResult();
        System.out.println(courseFromDb);
        session.close();
    }
}

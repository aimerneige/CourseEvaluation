package com.aimerneige.course_evaluation.mail;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}

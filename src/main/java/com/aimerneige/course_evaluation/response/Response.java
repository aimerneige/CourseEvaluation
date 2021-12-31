package com.aimerneige.course_evaluation.response;

public class Response {
    private Integer status;
    private String message;
    private Object data;

    private Response() {
    }

    private Response(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    private Response(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static Response success() {
        return new Response(200, "success");
    }

    public static Response success(Object data) {
        return new Response(200, "success", data);
    }

    public static Response created() {
        return new Response(201, "created");
    }

    public static Response created(Object data) {
        return new Response(201, "created", data);
    }

    public static Response noContent() {
        return new Response(204, "no content");
    }

    public static Response noContent(Object data) {
        return new Response(204, "no content", data);
    }

    public static Response badRequest() {
        return new Response(400, "bad request");
    }

    public static Response badRequest(Object data) {
        return new Response(400, "bad request", data);
    }

    public static Response unauthorized() {
        return new Response(401, "unauthorized");
    }

    public static Response unauthorized(Object data) {
        return new Response(401, "unauthorized", data);
    }

    public static Response forbidden() {
        return new Response(403, "forbidden");
    }

    public static Response forbidden(Object data) {
        return new Response(403, "forbidden", data);
    }

    public static Response notFound() {
        return new Response(404, "not found");
    }

    public static Response notFound(Object data) {
        return new Response(404, "not found", data);
    }

    public static Response conflict() {
        return new Response(409, "conflict");
    }

    public static Response conflict(Object data) {
        return new Response(409, "conflict", data);
    }

    public static Response internalServerError() {
        return new Response(500, "internal server error");
    }

    public static Response internalServerError(Object data) {
        return new Response(500, "internal server error", data);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

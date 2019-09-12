package com.redDabbler.template.tools.model;

import java.io.Serializable;
import java.util.List;

public class Result<T> implements Serializable{
    private T data;
    private int code;
    private String msg;
    private List list;
    private Page page;

    public Result(){

    }

    public static Result build(){
        return new Result();
    }

    public Result ok(T data){
        this.code = 200;
        this.data = data;
        return this;
    }

    public Result error(int code,String message){
        this.code = code;
        this.msg = message;
        return this;
    }

    public Result error(int code,Throwable throwable){
        this.code = code;
        this.msg = throwable.getMessage();
        return this;
    }

    public Result unexpectedError(Throwable throwable){
        this.code = 500;
        this.msg = throwable.getMessage();
        return this;
    }

    public Result list(List<T> dataList){
        this.list = dataList;
        return this;
    }

    public Result page(List<T> data){
        page = new Page();
        page.setData(data);
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}

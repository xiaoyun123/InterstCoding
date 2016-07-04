package com.zhang.bean;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;

/**
 * Created by zhang on 2016/5/28.
 */
@XStreamAlias("oschina")
public class Bean2 {
    public String catalog;
    public String newsCount;
    public String pagesize;
    public MyNews newslist;
    public class MyNews{
        @XStreamImplicit
        public ArrayList<MyNew> news;
    }
    public class MyNew{
        public String id;
        public String title;
        public String body;
        public String commentCount;
        public String author;
        public String authorid;
        public String pubDate;
        public String url;
        public Newss newstype;
    }
    public class Newss{
        public String type;
        public String authoruid2;
        public String eventurl;
        public String attachment;
    }

    public ArrayList<MyNew> getData2(String xml){
        XStream stream=new XStream();
        stream.processAnnotations(Bean2.class);
        Bean2 bean =(Bean2) stream.fromXML(xml);
        ArrayList<MyNew> list =bean.newslist.news;
        return list;
    }
}
package com.ec327.chatterbox.chatterbox;

public class UserThread {

    private String Id;
    private String title;
    private String season;
    private String episode;
    private String writer;
    private String content;
    private String comments;
    private String createdAt;

    //This is a "My Activity" Specific thread object that stores and sends the required info to the ViewMyThread screen.
    UserThread(String threadId, String threadTitle, String threadSeason, String threadEpisode, String threadWriter, String threadContent, String threadComments, String threadDate) {
        Id = threadId;
        title = threadTitle;
        season = threadSeason;
        episode = threadEpisode;
        writer = threadWriter;
        content = threadContent;
        comments = threadComments;
        createdAt = threadDate;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComments() {
        return comments;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString(){
        if(comments.length()!=0)
            return "[COMMENT] " + this.getComments() + "\n" + this.getCreatedAt();
        else
            return "[THREAD] " + this.getTitle() + "\n" + this.getCreatedAt();
    }
}

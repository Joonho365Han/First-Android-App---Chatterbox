package com.ec327.chatterbox.chatterbox;

public class Thread {

    private String Id;
    private String title;
    private String season;
    private String episode;

    //This is a thread object that stores the minimum necessary onfo to connect to the
    //Parse cloud and retrieve thread specific info.
    Thread(String threadId, String threadTitle, String threadSeason, String threadEpisode) {
        Id = threadId;
        title = threadTitle;
        season = threadSeason;
        episode = threadEpisode;
    }

    public String getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public String getSeason() {
        return season;
    }

    public String getEpisode() {
        return episode;
    }

    //This function formatts and outputs the way threads are displayed as a list.
    @Override
    public String toString(){
        return this.getTitle() + "\n<Season" + this.getSeason() + " Episode" + this.getEpisode() + ">";
    }
}

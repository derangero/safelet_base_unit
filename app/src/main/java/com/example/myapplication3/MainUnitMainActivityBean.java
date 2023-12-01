package com.example.myapplication3;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.VideoView;

import constants.MainUnitDisplayMode;

public class MainUnitMainActivityBean {

    private MainUnitDisplayMode displayMode;
    private VideoView idleVideoView;
    private SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    private View initialView;

    private View waitingView;

    private View announceClerkView;

    private View touchButton;
    private long elapsed;
    public MainUnitMainActivityBean() {
        displayMode = MainUnitDisplayMode.NONE;
        elapsed = 0L;
    }

    public SurfaceView getSurfaceView() {
        return surfaceView;
    }

    public void setSurfaceView(SurfaceView surfaceView) {
        this.surfaceView = surfaceView;
    }

    public MainUnitDisplayMode getDisplayMode() {
        return displayMode;
    }

    public void setDisplayMode(MainUnitDisplayMode displayMode) {
        this.displayMode = displayMode;
    }

    public VideoView getIdleVideoView() {
        return idleVideoView;
    }

    public void setIdleVideoView(VideoView idleVideoView) {
        this.idleVideoView = idleVideoView;
    }

    public View getInitialView() {
        return initialView;
    }

    public void setInitialView(View initialView) {
        this.initialView = initialView;
    }

    public SurfaceHolder getSurfaceHolder() {
        return surfaceHolder;
    }

    public View getWaitingView() {
        return waitingView;
    }

    public void setWaitingView(View waitingView) {
        this.waitingView = waitingView;
    }

    public View getAnnounceClerkView() {
        return announceClerkView;
    }

    public void setAnnounceClerkView(View announceClerkView) {
        this.announceClerkView = announceClerkView;
    }

    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    public View getTouchButton() {
        return touchButton;
    }

    public void setTouchButton(View touchButton) {
        this.touchButton = touchButton;
    }

    public long getElapsed() {
        return elapsed;
    }

    public void setElapsed(long elapsed) {
        this.elapsed = elapsed;
    }
}

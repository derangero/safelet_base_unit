package com.example.myapplication3;
import android.app.AlarmManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.View;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import constants.MainUnitDisplayMode;

public class UDPReceptionController extends Thread implements SurfaceHolder.Callback {
    private MainUnitMainActivityBean mainActivityBean;
    private Handler mainHandler;
    private DatagramSocket socket;

    private boolean isRunning;
    public UDPReceptionController(MainUnitMainActivityBean mainActivityBean, Handler mainHandler){
        this.mainActivityBean = mainActivityBean;
        this.mainHandler = mainHandler;
        this.isRunning = true;
    }

    public void run() {
        System.out.println("UDP通信_待ち受けを開始します。");
        while (this.isRunning) {
            // UDP受信
            try {
                this.socket = UDPUtil.createSocket();
                String command = receive();
                switch(command){
                    case "s00":
                        // 初期画面
                        mainHandler.post(() -> {
                            System.out.println("UDP通信_s00コマンドを受信しました");
                            mainActivityBean.setDisplayMode(MainUnitDisplayMode.NONE);
                            mainActivityBean.getInitialView().setVisibility(View.VISIBLE);
                            mainActivityBean.getWaitingView().setVisibility(View.INVISIBLE);
                            mainActivityBean.getAnnounceClerkView().setVisibility(View.INVISIBLE);
                            mainActivityBean.getTouchButton().setVisibility(View.INVISIBLE);

                            mainActivityBean.getIdleVideoView().setVisibility(View.INVISIBLE);

                        });
                        mainActivityBean.setElapsed(0L);
                        break;
                    case "s01":
                        //お待ちください
                        mainHandler.post(() -> {
                            System.out.println("UDP通信_s01コマンドを受信しました");
                            mainActivityBean.setDisplayMode(MainUnitDisplayMode.WAITING);

                            mainActivityBean.getInitialView().setVisibility(View.INVISIBLE);
                            mainActivityBean.getWaitingView().setVisibility(View.VISIBLE);
                            mainActivityBean.getAnnounceClerkView().setVisibility(View.INVISIBLE);
                            mainActivityBean.getTouchButton().setVisibility(View.INVISIBLE);

                            mainActivityBean.getIdleVideoView().setVisibility(View.VISIBLE);
                            //最前面に描画
                            //mainActivityBean.getIdleVideoView().setZOrderOnTop(true);
                        });
                        mainActivityBean.setElapsed(0L);
                        break;
                    case "s04":
                        //店員にお知らせください
                        mainHandler.post(() -> {
                            System.out.println("UDP通信_s04コマンドを受信しました");
                            mainActivityBean.setDisplayMode(MainUnitDisplayMode.ANNOUNCE_CLERK);

                            mainActivityBean.getInitialView().setVisibility(View.INVISIBLE);
                            mainActivityBean.getWaitingView().setVisibility(View.INVISIBLE);
                            mainActivityBean.getAnnounceClerkView().setVisibility(View.VISIBLE);
                            mainActivityBean.getTouchButton().setVisibility(View.INVISIBLE);

                            mainActivityBean.getIdleVideoView().setVisibility(View.INVISIBLE);
                            //mainActivityBean.getIdleVideoView().setZOrderOnTop(true);
                        });
                        mainActivityBean.setElapsed(0L);
                        break;
                        //TODO:のぞき防止
                    case "alert":
                        System.out.println("alert コマンドを受信しました。");
                        mainActivityBean.setElapsed(0L);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String receive() throws IOException {
        // udpを受信して、文字列にして返す
        byte[] buff = new byte[UDPConstants.UDP_BUFFER_SIZE];
        DatagramPacket packet = new DatagramPacket(buff, UDPConstants.UDP_BUFFER_SIZE);
        this.socket.receive(packet);
        this.socket.close();

        return new String(buff).trim();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}

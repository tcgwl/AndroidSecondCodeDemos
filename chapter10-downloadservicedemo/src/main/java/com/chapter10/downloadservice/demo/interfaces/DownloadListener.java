package com.chapter10.downloadservice.demo.interfaces;

/**
 * Created by Archer on 2017/10/25.
 */

public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onPaused();
    void onCanceled();
}

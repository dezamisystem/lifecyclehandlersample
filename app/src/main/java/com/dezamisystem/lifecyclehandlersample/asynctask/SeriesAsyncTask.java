package com.dezamisystem.lifecyclehandlersample.asynctask;

import android.os.AsyncTask;

import org.jetbrains.annotations.Nullable;

public class SeriesAsyncTask extends AsyncTask<String,Void,String> {

    @Nullable
    private Callback callback = null;

    @Override
    protected String doInBackground(String... params) {
        if (callback != null) {
            callback.execute();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String result) {

    }

    public SeriesAsyncTask setOnCallback(final Callback callback) {
        this.callback = callback;
        return this;
    }

    public static class Callback {
        public void execute() {

        }
    }
}

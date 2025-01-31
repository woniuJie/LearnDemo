package com.zsj.learndemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by zhangshijie on 2019/8/20;
 */
public class AppStateTracker {
    public static final int STATE_FOREGROUND = 0;

    public static final int STATE_BACKGROUND = 1;

    private static int currentState;

    public static int getCurrentState() {
        return currentState;
    }

    public interface AppStateChangeListener {
        void appTurnIntoForeground();
        void appTurnIntoBackGround();
    }

    public static void track(Application application, final AppStateChangeListener appStateChangeListener){

        application.registerActivityLifecycleCallbacks(new SimpleActivityLifecycleCallbacks(){

            private int resumeActivityCount = 0;

            @Override
            public void onActivityStarted(Activity activity) {
                if (resumeActivityCount==0){
                    currentState = STATE_FOREGROUND;
                    appStateChangeListener.appTurnIntoForeground();
                }

                resumeActivityCount++;
            }


            @Override
            public void onActivityStopped(Activity activity) {
                resumeActivityCount--;

                if (resumeActivityCount==0){
                    currentState = STATE_BACKGROUND;
                    appStateChangeListener.appTurnIntoBackGround();
                }

            }
        });
    }

    private static class SimpleActivityLifecycleCallbacks implements Application
            .ActivityLifecycleCallbacks{

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }


}

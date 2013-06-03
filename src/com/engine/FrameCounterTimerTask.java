package com.engine;

import java.util.TimerTask;

public class FrameCounterTimerTask extends TimerTask {

        @Override
        public void run() {
        	EngineSettings.realFrameRate = EngineSettings.currentFrameCounter;
        	EngineSettings.currentFrameCounter = 0;
        }
}

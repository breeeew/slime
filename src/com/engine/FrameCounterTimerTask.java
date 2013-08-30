package com.engine;

import java.util.TimerTask;

public class FrameCounterTimerTask extends TimerTask {

        @Override
        public void run() {
        	Engine.realFrameRate = Engine.currentFrameCounter;
        	Engine.currentFrameCounter = 0;
        }
}

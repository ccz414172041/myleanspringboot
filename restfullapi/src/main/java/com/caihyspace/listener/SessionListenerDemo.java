package com.caihyspace.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * session监听器 servlet提供API
 */
@WebListener
@Slf4j
public class SessionListenerDemo implements HttpSessionListener {

    private static int activeSessions = 0;

    /**
     * 返回在线session数量
     * @return int
     */
    public static int getActiveSesions() {
        return activeSessions;
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("session init");
        activeSessions++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("session destroyed");
        if (activeSessions > 0)
            activeSessions--;
    }
}

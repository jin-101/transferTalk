package listener;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import crawling.Crawling;

/**
 * Application Lifecycle Listener implementation class InitCrawling
 *
 */
@WebListener
public class InitCrawling implements ServletContextListener {

    public InitCrawling() {
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    }

    public void contextInitialized(ServletContextEvent sce)  { // 실행시 수행 크롤링
//    	Crawling crawling = new Crawling();
//    	try {
//			crawling.crawl();
////    		crawling.crawlTeams();
////			crawling.crawlPlayerImageSource();
////    		crawling.crawlTeamImageSource();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
    }
	
}
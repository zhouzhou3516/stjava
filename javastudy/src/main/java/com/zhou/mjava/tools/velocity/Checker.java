package com.zhou.mjava.tools.velocity;

import org.apache.camel.processor.LoggingErrorHandler;
import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liqingzhou on 18/1/23
 */
public class Checker {
    static Logger logger = LoggerFactory.getLogger(Checker.class);
    public static void main(String[] args) {
        try
        {

            Velocity.init();
            Template template = Velocity.getTemplate("mytemplate.vm");
        } catch( ParseErrorException pee )
        {
            //模板有语法错误，报错
            logger.info("有语法错误，请修改");
        }
        catch( Exception e )
        {
            //其他错误
            //...
        }
    }

}

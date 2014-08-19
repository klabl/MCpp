package mod.pwngu.lib;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PWNLogger {

    private static PWNLogger instance;

    public static PWNLogger getLogger() {

        if(instance == null) instance = new PWNLogger();
        return instance;
    }

    public final Logger log;

    private PWNLogger() {

        log = LogManager.getLogger("FML.MCpp");
    }

    public synchronized void ftl(Object ftl) {

        if(ftl instanceof Throwable) {

            ftl("", (Throwable) ftl);
            return;
        }

        log.log(Level.FATAL, String.valueOf(ftl));
    }

    public synchronized void ftl(Object ftl, Throwable ex) {

        log.log(Level.FATAL, String.valueOf(ftl), ex);
    }

    public synchronized void err(Object err) {

        if(err instanceof Throwable) {

            err("", (Throwable) err);
            return;
        }

        log.log(Level.ERROR, String.valueOf(err));
    }

    public synchronized void err(Object err, Throwable ex) {

        log.log(Level.ERROR, String.valueOf(err), ex);
    }

    public synchronized void wrn(Object wrn) {

        if(wrn instanceof Throwable) {

            wrn("", (Throwable) wrn);
            return;
        }

        log.log(Level.WARN, String.valueOf(wrn));
    }

    public synchronized void wrn(Object wrn, Throwable ex) {

        log.log(Level.WARN, String.valueOf(wrn), ex);
    }

    public synchronized void inf(Object inf) {

        log.log(Level.INFO, String.valueOf(inf));
    }

    public synchronized void dbg(Object dbg) {

        if(dbg instanceof Throwable) {

            dbg("", (Throwable) dbg);
            return;
        }

        log.log(Level.DEBUG, String.valueOf(dbg));
    }

    public synchronized void dbg(Object dbg, Throwable ex) {

        log.log(Level.DEBUG, String.valueOf(dbg), ex);
    }

    public synchronized void trc(Object dbg) {

        log.log(Level.TRACE, String.valueOf(dbg));
    }
}
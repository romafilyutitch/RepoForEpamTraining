package by.epam.jwd.service;

public class SimpleLogServiceFactory implements LogServiceFactory{
    private SimpleLogServiceFactory(){}

    public static SimpleLogServiceFactory newInstance(){
        return new SimpleLogServiceFactory();
    }
    @Override
    public LogService getLogService() {
        return new SimpleLogService();
    }
}

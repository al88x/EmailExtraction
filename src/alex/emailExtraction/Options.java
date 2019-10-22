package alex.emailExtraction;

public class Options {

    public static final String DECLINED_DOMAIN = "--D:";
    public static final String FREQUENCY = "--F:";
    private String[] domainIgnoreList;
    private Integer minimumFrequency;


    public Options(String[] args) {
        initialiseFields(args);
    }

    private void initialiseFields(String[] args) {
        for (String arg : args) {
            if (arg.substring(0, 4).equals(DECLINED_DOMAIN)) {
                this.domainIgnoreList = arg.substring(4).split(",");
            }
            if (arg.substring(0, 4).equals(FREQUENCY)) {
                this.minimumFrequency = Integer.valueOf(arg.substring(4));
            }
        }
    }

    public boolean isDomainInIgnoreList(String domain) {
        for (String entry : domainIgnoreList) {
            if (domain.equals(entry)) {
                return true;
            }
        }
        return false;
    }

    public Integer getMinimumFrequency() {
        return minimumFrequency;
    }
}

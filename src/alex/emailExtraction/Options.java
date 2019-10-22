package alex.emailExtraction;

public class Options {

    private String[] args;
    private String[] domainIgnoreList;
    private Integer minimumFrequency;


    public Options(String[] args) {
        this.args = args;
        initialiseFields(args);
    }

    private void initialiseFields(String[] args) {
        for (String arg : args) {
            if(arg.substring(0,4).equals("--D:")){
                this.domainIgnoreList = arg.substring(4).split(",");
            }
            if(arg.substring(0,4).equals("--F:")){
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

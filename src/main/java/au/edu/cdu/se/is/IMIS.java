package au.edu.cdu.se.is;

import au.edu.cdu.se.util.is.ISGlobalVariable;

public interface IMIS {
    void setGv(ISGlobalVariable gv);

    void setAp(ISAlgoParam ap);

    int run();
}

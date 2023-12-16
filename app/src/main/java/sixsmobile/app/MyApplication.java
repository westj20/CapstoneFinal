package sixsmobile.app;

import android.app.Application;

public class MyApplication extends Application {
    private AuditData auditData;

    public AuditData getAuditData() {
        return auditData;
    }
    public void setAuditData(AuditData auditData) {
        this.auditData = auditData;
    }
}

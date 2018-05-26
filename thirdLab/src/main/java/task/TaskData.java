package task;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class TaskData implements Serializable{

    private double arg = Double.NaN;
    private double base = Double.NaN;

    @XmlTransient
    private boolean status = false;
    private double result = Double.NaN;

    public TaskData() {
    }

    public TaskData(double arg, double base) {
        this.arg = arg;
        this.base = base;
    }


    public boolean status() {
        if (!status) {
            status = !Double.isNaN(arg) && !Double.isNaN(base);
        }
        return status;
    }

    public void performTask() {
        result = (Math.log(arg) / Math.log(base));
    }

    public Double getArg() {
        return arg;
    }

    public void setArg(Double arg) {
        this.arg = arg;
    }

    public Double getBase() {
        return base;
    }

    public void setBase(Double base) {
        this.base = base;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }
}

package task;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class TaskData implements Serializable{
    private String text;
    private String subString;
    @XmlTransient
    private boolean status = false;
    private String newText = null;

    public TaskData() {
    }

    public TaskData(String text, String subString) {
        this.text = text;
        this.subString = subString;
    }


    public boolean status() {
        if (!status) {
            if (text != null && subString != null) {
                status = true;
                return status;
            }
        }
        return status;
    }

    public void performTask() {
        if (newText == null) {
            //newText = text.replaceAll(subString,"");
            double a, b;
            a= Double.parseDouble(text);
            b=Double.parseDouble(subString);
            newText=  String.valueOf( a/b);
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubString() {
        return subString;
    }

    public void setSubString(String subString) {
        this.subString = subString;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNewText() {
        return newText;
    }
}

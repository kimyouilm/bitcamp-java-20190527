package ch29.d;

public class BlackBox {
  private String maker;
  private String model;
  
  public BlackBox() {
    System.out.println("BlackBox()+뀨");
  }
  
  @Override
  public String toString() {
    return "BlackBox [maker=" + maker + ", model=" + model + "]";
  }
  public String getMaker() {
    return maker;
  }
  public void setMaker(String maker) {
    System.out.println("BlackBox.setMaker()+뀨");
    this.maker = maker;
  }
  public String getModel() {
    return model;
  }
  public void setModel(String model) {
    System.out.println("BlackBox.setModel()+뀨");
    this.model = model;
  }
  
  
}

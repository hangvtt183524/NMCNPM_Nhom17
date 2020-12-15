package entities;

import java.sql.SQLException;

import controller.RecordInformation;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Result {
	private String cert;
	private String name;
	private String number;
	private String time;
	private String method;
	private String result;
	private Button update;
	
	public Result(String cert, String name, String number, String time, String method, String result)
	{
		this.cert = cert;
		this.name = name;
		this.number = number;
		this.time = time;
		this.method = method;
		this.result = result;
		setUpdate();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCert() {
		return cert;
	}

	public void setId_nhan_khau(String id_nhan_khau) {
		this.cert = id_nhan_khau;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	
	

	public Button getUpdate() {
		return update;
	}

	public void setUpdate() {
		this.update = new Button("Update");
		setUpdateButtonEventHandle();
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	private void setUpdateButtonEventHandle()
	{
		EventHandler eventHandler = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) {
				   RecordInformation recordInfo = new RecordInformation();
				   try {
					recordInfo.query_change("update quan_ly_kiem_tra set ket_qua = ? where cccd = ? and lan_thu = ?;");
					recordInfo.getPreStatement().setString(1, result);
					recordInfo.getPreStatement().setString(2, cert);
					recordInfo.getPreStatement().setString(3, number);
					recordInfo.getPreStatement().execute();
					
					recordInfo.closeState();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			   } 
			};
		this.update.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
	}	
}
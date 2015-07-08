package nju.iip.dto;

/** 
 * 私信消息类 
 *  
 * @author mrpod2g 
 */  
public class Message extends BaseMessage {  

	// 消息内容  
    private String Content;  
    
    //发送者的openid
    private String fromOpenId;
    
    //接受者的opendid
    private String toOpenId;
    
    //发送者的微信昵称
    private String fromNickname;
    
    //接受者的微信昵称
    private String toNickname;
    
    //发送者的微信头像
    private String fromHeadImgUrl;
    
    //消息是否被读过  0:未读过          1：读过
    private int isRead;
    
    //发送时间
    private String sendTime;
    
    public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	public String getFromOpenId() {
		return fromOpenId;
	}

	public void setFromOpenId(String fromOpenId) {
		this.fromOpenId = fromOpenId;
	}

	public String getToOpenId() {
		return toOpenId;
	}

	public void setToOpenId(String toOpenId) {
		this.toOpenId = toOpenId;
	}

	public String getFromNickname() {
		return fromNickname;
	}

	public void setFromNickname(String fromNickname) {
		this.fromNickname = fromNickname;
	}

	public String getToNickname() {
		return toNickname;
	}

	public void setToNickname(String toNickname) {
		this.toNickname = toNickname;
	}

	public String getFromHeadImgUrl() {
		return fromHeadImgUrl;
	}

	public void setFromHeadImgUrl(String fromHeadImgUrl) {
		this.fromHeadImgUrl = fromHeadImgUrl;
	}
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }  
}  

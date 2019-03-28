/*
 * 版权所有 2017 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.util.constant;

/**
 * 短消息常量
 * @since 2017-7-17
 */
public class MessageConstant {
	//创建任务后发送的提示信息
	public static final String CREATE_TASK_ORG_MSG = "任务已配置完成并发布，任务可进行自行管理开关状态。";
	//填报机构接受到的提示信息
	public static final String FILL_TASK_ORG_MSG = "任务已下发本机构，请及时安排人员进行报表填报工作。";
	//机构信息变更被驳回后的提示信息
	public static final String SUBJECT_UPDATE_ORG_MSG = "您提交的机构信息变更申请被驳回。";
	//催报的提示信息
	public static final String URGE_ORG_MSG = "数据未上报，请及时组织人员进行报表填报工作。";
	// 数据上报提示审核信息
	public static final String PASS_TO = "数据已经上报，请尽快审核";
	// 驳回下级机构提示信息
	public static final String REJECT_MESSAGE = "您的数据被上级主管单位驳回，请尽快核查数据,驳回原因:";
	//有效
	public static final String VALID_STATUS = "0";
	//无效
	public static final String INVALID_STATUS = "1";
	//通知
	public static final String NOTICE_CATEGORY = "0";
	//公告
	public static final String ANNOUNCEMENT_CATEGORY = "1";
	//默认消息标题
	public static final String TITLE = "系统提醒";
	// 已读
	public static final String READ = "1";
	// 未读
	public static final String UNREAD = "0";
	//催报类型，站内提醒
	public static final String STATION_SEND_TYPE = "0";
	//催报类型，短信提醒
	public static final String MESSAGE_SEND_TYPE = "1";
	// 通知公告
	public static final String NOTICE_RECORD = "2";
	// 下载文件
	public static final String NOTICE_FILE = "3";
	// 提示停止服务器
	public static final String STOP_SERVICE = "STOP";

}

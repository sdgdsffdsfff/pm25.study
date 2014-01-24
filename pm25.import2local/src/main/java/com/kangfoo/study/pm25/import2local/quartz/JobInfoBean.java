package com.kangfoo.study.pm25.import2local.quartz;

import java.io.Serializable;

public class JobInfoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6779199800255028555L;
	private String triggerName;
	private String group;
	private String jobName;
	private String jobClass;
	private String cronExpression;
	private String operationType;

	/**
	 * 请保证在 jobName 和 groupId 是唯一的（联合主键）
	 */
	@Deprecated
	public JobInfoBean() {
	}

	public JobInfoBean(String jobClass, String jobName, String group,
			String cronExpression) {
		this.jobName = jobName;
		this.group = group;
		this.jobClass = jobClass;
		this.triggerName = "trigger_" + jobName;
		this.cronExpression = cronExpression;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
}

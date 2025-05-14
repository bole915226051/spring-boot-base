# 测试环境地址

http://22.144.103.130:30374

# 生产环境地址

http://20.144.200.10:30374

# 版本

| 版本  | 操作人 |
|-----|-----|
| 1.0 | 李源青 |

# 登录接口

## 地址

/paas-web/upmstreeapi/login

## 请求方式

`POST`

## Header

`Content-Type:application/json`

## req

| 字段名          | 类型     | 描述        | 是否必填 | 备注                               |
|--------------|--------|-----------|------|----------------------------------|
| userName     | String | 用户名       | 是    | autoplatform                     |
| password     | String | 密码        | 是    | QWRtaW4xMjM=                     |
| clientId     | String | 客户端id     | 是    | e3a15b87f4f143428e7269f4d885af02 |
| typeConfigId | String | 用户类型的配置ID | 是    | 0                                |

### req.demo

```
{
	"userName": "autoplatform",
	"password": "QWRtaW4xMjM=",
	"typeConfigId": 0,
	"clientId": "e3a15b87f4f143428e7269f4d885af02"
}
```

## res

| 字段名  | 类型     | 描述       | 是否必填 | 备注           |
|------|--------|----------|------|--------------|
| code | String | 授权码相关    |      | 用于登录         |
| id   | Long   | 当前登录用户主键 |      |              |
| ...  |        |          |      | 其他字段不使用,暂不解释 |

### res.demo

```
{
	"state": "success",
	"code": 200,
	"data": {
		"name": "自动化平台",
		"login": "autoplatform",
		"email": "autoplatform@qq.com",
		"phone": "15544667788",
		"company": "",
		"source": 0,
		"sourceId": "",
		"ldapOrgDn": "",
		"firstLogin": 1,
		"state": 0,
		"accountType": 0,
		"id": 310000105,
		"isDeleted": 0,
		"createTime": "2025-05-09 11:12:22",
		"creator": 310000089,
		"updateTime": "2025-05-13 14:42:35",
		"mender": 888888,
		"sourceConfigId": 0,
		"code": "ae69592752b54457999e4cf34eea302f",
		"sessionId": "65cf27979a2c41c391c4c070398a7cdb",
		"ssoFlag": 1
	}
}
```

# 获取`token`接口

## 说明

- 通过登录接口的返回结果,`data.code`获取`refreshToken` `token`;
- 每次请求,携带`refreshToken` `token`则通过鉴权校验。

## 地址

/paas-web/upmstreeapi/accessToken?code=ae69592752b54457999e4cf34eea302f

## 请求方式

`GET`

## Header

## req

## res

```
{
	"code": 200,
	"data": {
		"token": "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMjgwMjJ9.o4fDMDNZMdsVhbmv2I9mBaaM9I2t_BkUraVaMpU76z3rtnlPYR07K73Ltwp0W7fIaidfxGG3TzhiLT0miZVkE8Kf7il-lM6ZJFoHhQ7_IMOQdD9cCSFCbXkWzXTS79s_rZb9EC_e6NldhLz3vf7vRhXgq8ylmFTUz54Y0XK6cY4",
		"refreshToken": "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMzE2MjF9.cMcPXtQ3ov4Ks80Gc8FPl__G7j1DAlerIeMHX7b_AL8SQQNqxGGUmQebe4ycxVy3YmNjgzW7QHGoIfwBn42-GopSKeip27xz3a6GxeVFMt4n0WN5KZnivWUjBKF0Tmr8vBWdWjdYDJZG3bkENiXZA8-DUsMp3xPhASBBKn8Np7A",
		"expiredTime": "2025-05-13 18:20:22",
		"sessionId": "24946f4cf28742f9981979535ac3abfb"
	},
	"logout": false,
	"state": "success"
}
```

# 获取当前所有项目

## 说明

## 地址

/paas-web/devopsplatform/project/allUserProjectList

## 请求方式

`GET`

## Header

```
refreshToken:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMjgwMjJ9.o4fDMDNZMdsVhbmv2I9mBaaM9I2t_BkUraVaMpU76z3rtnlPYR07K73Ltwp0W7fIaidfxGG3TzhiLT0miZVkE8Kf7il-lM6ZJFoHhQ7_IMOQdD9cCSFCbXkWzXTS79s_rZb9EC_e6NldhLz3vf7vRhXgq8ylmFTUz54Y0XK6cY4
token:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMzE2MjF9.cMcPXtQ3ov4Ks80Gc8FPl__G7j1DAlerIeMHX7b_AL8SQQNqxGGUmQebe4ycxVy3YmNjgzW7QHGoIfwBn42-GopSKeip27xz3a6GxeVFMt4n0WN5KZnivWUjBKF0Tmr8vBWdWjdYDJZG3bkENiXZA8-DUsMp3xPhASBBKn8Np7A
```

## req

## res

| 字段名         | 类型      | 描述    | 是否必填 | 备注               |
|-------------|---------|-------|------|------------------|
| id          | Integer | 项目主键  |      | 其他地方使用为projectId |
| name        | String  | 项目名称  |      |                  |
| description | String  | 项目描述  |      |                  |
| chargeManId | Integer | 项目负责人 |      |                  |
| type        | String  | 项目类型  |      |                  |
| ...         |         |       |      | 其他字段不使用,暂不解释     |

### res.demo

```
{
	"success": true,
	"message": "操作成功",
	"code": 200,
	"timestamp": 1747120922337,
	"data": [
		{
			"id": 282,
			"projectKey": "ZWJCESHIYEWULEIXIANG",
			"name": "ZWJ测试业务类项目",
			"description": null,
			"chargeManId": 310000101,
			"type": "3",
			"category": "YEWU",
			"logo": null,
			"jiraProjectId": 0,
			"jiraProjectKey": "",
			"createTime": "2025-05-12T09:40:49",
			"updateTime": "2025-05-12T09:40:49",
			"removed": null,
			"isDisable": null
		},
		{
			"id": 281,
			"projectKey": "TEST0506",
			"name": "test0506",
			"description": null,
			"chargeManId": 310000089,
			"type": "AGILE",
			"category": "BOCLOUD",
			"logo": null,
			"jiraProjectId": 0,
			"jiraProjectKey": "",
			"createTime": "2025-05-06T15:17:55",
			"updateTime": "2025-05-06T15:17:55",
			"removed": null,
			"isDisable": null
		},
		{
			"id": 280,
			"projectKey": "CESHIWUQUAN",
			"name": "测试无权限postman创建",
			"description": null,
			"chargeManId": 310000099,
			"type": "AGILE",
			"category": "BOCLOUD",
			"logo": null,
			"jiraProjectId": 0,
			"jiraProjectKey": "",
			"createTime": "2025-04-29T15:49:46",
			"updateTime": "2025-04-29T15:49:46",
			"removed": null,
			"isDisable": null
		},
		{
			"id": 279,
			"projectKey": "CESHIWUQUANXI",
			"name": "测试无权限调用-fff",
			"description": null,
			"chargeManId": 310000099,
			"type": "AGILE",
			"category": "BOCLOUD",
			"logo": null,
			"jiraProjectId": 0,
			"jiraProjectKey": "",
			"createTime": "2025-04-29T15:38:26",
			"updateTime": "2025-04-29T15:38:26",
			"removed": null,
			"isDisable": null
		},
		{
			"id": 278,
			"projectKey": "CESHIWUQUANXIANDI",
			"name": "测试无权限调用-ddd",
			"description": null,
			"chargeManId": 310000089,
			"type": "AGILE",
			"category": "BOCLOUD",
			"logo": null,
			"jiraProjectId": 0,
			"jiraProjectKey": "",
			"createTime": "2025-04-29T15:34:47",
			"updateTime": "2025-04-29T15:34:47",
			"removed": null,
			"isDisable": null
		},
		{
			"id": 277,
			"projectKey": "CESHIWUQUANXIANDIAOY",
			"name": "测试无权限调用",
			"description": null,
			"chargeManId": 310000089,
			"type": "AGILE",
			"category": "BOCLOUD",
			"logo": null,
			"jiraProjectId": 0,
			"jiraProjectKey": "",
			"createTime": "2025-04-29T15:32:12",
			"updateTime": "2025-04-29T15:32:12",
			"removed": null,
			"isDisable": null
		},
		{
			"id": 276,
			"projectKey": "TESTCESHISHISHIXUQIU",
			"name": "TEST测试史诗-需求-任务0422",
			"description": "针对测试建的项目",
			"chargeManId": 310000002,
			"type": "3",
			"category": "YEWU",
			"logo": null,
			"jiraProjectId": 0,
			"jiraProjectKey": "",
			"createTime": "2025-04-22T15:12:51",
			"updateTime": "2025-05-09T10:07:46",
			"removed": null,
			"isDisable": null
		},
		{
			"id": 275,
			"projectKey": "LYQDDUD",
			"name": "lyq-ddud",
			"description": "ddud",
			"chargeManId": 310000099,
			"type": "3",
			"category": "YEWU",
			"logo": null,
			"jiraProjectId": 0,
			"jiraProjectKey": "",
			"createTime": "2025-04-17T17:54:24",
			"updateTime": "2025-05-09T10:07:46",
			"removed": null,
			"isDisable": null
		},
		{
			"id": 274,
			"projectKey": "LIYUANQINGCHUANGJIAN",
			"name": "李源青创建业务项目流程梳理",
			"description": "李源青创建业务项目流程梳理",
			"chargeManId": 310000089,
			"type": "3",
			"category": "YEWU",
			"logo": null,
			"jiraProjectId": 0,
			"jiraProjectKey": "",
			"createTime": "2025-04-16T15:41:54",
			"updateTime": "2025-05-08T17:49:35",
			"removed": null,
			"isDisable": null
		},
		{
			"id": 272,
			"projectKey": "KEJILEI",
			"name": "科技类",
			"description": "科技类",
			"chargeManId": 310000089,
			"type": "3",
			"category": "YEWU",
			"logo": null,
			"jiraProjectId": 0,
			"jiraProjectKey": "",
			"createTime": "2025-04-16T11:06:54",
			"updateTime": "2025-04-16T11:06:54",
			"removed": null,
			"isDisable": null
		},
		{
			"id": 271,
			"projectKey": "W1232",
			"name": "23432",
			"description": "2343",
			"chargeManId": 310000085,
			"type": "3",
			"category": "YEWU",
			"logo": null,
			"jiraProjectId": 0,
			"jiraProjectKey": "",
			"createTime": "2025-04-09T15:53:11",
			"updateTime": "2025-05-09T10:07:46",
			"removed": null,
			"isDisable": null
		},
		{
			"id": 270,
			"projectKey": "DEVOPSII",
			"name": "DEVOPS-II",
			"description": "",
			"chargeManId": 310000089,
			"type": "AGILE",
			"category": "BOCLOUD",
			"logo": null,
			"jiraProjectId": 0,
			"jiraProjectKey": "",
			"createTime": "2025-04-01T15:14:49",
			"updateTime": "2025-04-01T15:14:49",
			"removed": null,
			"isDisable": null
		},
		{
			"id": 269,
			"projectKey": "LYQYEWUXIANGMU",
			"name": "LYQ-业务项目",
			"description": null,
			"chargeManId": 310000002,
			"type": "3",
			"category": "YEWU",
			"logo": null,
			"jiraProjectId": 0,
			"jiraProjectKey": "",
			"createTime": "2025-03-31T15:25:29",
			"updateTime": "2025-05-09T10:07:46",
			"removed": null,
			"isDisable": null
		},
		{
			"id": 268,
			"projectKey": "LYQDEMO",
			"name": "lyq-DEMO",
			"description": "李源青测试demo。",
			"chargeManId": 310000033,
			"type": "AGILE",
			"category": "BOCLOUD",
			"logo": null,
			"jiraProjectId": 0,
			"jiraProjectKey": "",
			"createTime": "2025-03-26T11:09:56",
			"updateTime": "2025-03-26T11:09:56",
			"removed": null,
			"isDisable": null
		}
	]
}
```

# 获取当前项目下的需求（分页）

## 说明

## 地址

/paas-web/devopssynergy/matter/list/STORY

## 请求方式

`POST`

## Header

```
Content-Type:application/json
refreshToken:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMjgwMjJ9.o4fDMDNZMdsVhbmv2I9mBaaM9I2t_BkUraVaMpU76z3rtnlPYR07K73Ltwp0W7fIaidfxGG3TzhiLT0miZVkE8Kf7il-lM6ZJFoHhQ7_IMOQdD9cCSFCbXkWzXTS79s_rZb9EC_e6NldhLz3vf7vRhXgq8ylmFTUz54Y0XK6cY4
token:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMzE2MjF9.cMcPXtQ3ov4Ks80Gc8FPl__G7j1DAlerIeMHX7b_AL8SQQNqxGGUmQebe4ycxVy3YmNjgzW7QHGoIfwBn42-GopSKeip27xz3a6GxeVFMt4n0WN5KZnivWUjBKF0Tmr8vBWdWjdYDJZG3bkENiXZA8-DUsMp3xPhASBBKn8Np7A
```

## req

| 字段名        | 类型      | 描述      | 是否必填 | 备注                 |
|------------|---------|---------|------|--------------------|
| ...        |         |         |      | 其他字段不使用,暂不解释       |
| projectId  | Integer | 项目类型    | 是    |                    |
| pageSize   | Integer | 页大小     | 是    |                    |
| pageNum    | Integer | 当前页     | 是    |                    |
| conditions |         | 自定义查询条件 | 否    | 建议使用简单一下,名称搜索,状态搜索 |

### conditions

| 字段名        | 类型     | 描述      | 是否必填 | 备注                        |
|------------|--------|---------|------|---------------------------|
| ...        |        |         |      | 其他字段不使用,暂不解释              |
| columnName | String | 字段名称    | 是    |                           |
| fieldId    | Long   | 自定义属性ID | 是    |                           |
| value      |        | 搜索条件值   | 否    | 如果为空,则当前conditions不参与条件判断 |

### value

| 字段名       | 类型            | 描述 | 是否必填 | 备注                          |
|-----------|---------------|----|------|-----------------------------|
| ...       |               |    |      | 其他字段不使用,暂不解释                |
| keyWord   | String        |    |      | 单行文本、多行文本输入参数               |
| checkIds  | List<Integer> |    |      | 单选菜单、多选菜单、单个成员选择、多个成员选择输入参数 |
| start     | BigDecimal    |    |      | 整数、小数搜索区间开始值                |
| end       | BigDecimal    |    |      | 整数、小数搜索区间结束值                |
| startDate | LocalDateTime |    |      | 日期、时间搜索区间开始值                |
| endDate   | LocalDateTime |    |      | 日期、时间搜索区间结束值                |

### req.demo

```
{
	"projectId": "270",
	"pageSize": 15,
	"pageNum": 1,
	"conditions": [
		{
			"fieldId": 1,
			"columnName": "title",
			"value": {
				"keyWord": null
			}
		},
		{
			"fieldId": 6,
			"columnName": "status",
			"value": {
				"checkIds": null
			}
		},
	],
	"matterType": "ALL"
}
```

## res

| 字段名     | 类型      | 描述 | 是否必填 | 备注           |
|---------|---------|----|------|--------------|
| ...     |         |    |      | 其他字段不使用,暂不解释 |
| current | Integer |    |      | 当前页          |
| size    | Integer |    |      | 当前页大小        |
| total   | Integer |    |      | 总数           |
| records | List    |    |      |              |

### records

| 字段名            | 类型            | 描述       | 是否必填 | 备注               |
|----------------|---------------|----------|------|------------------|
| ...            |               |          |      | 其他字段不使用,暂不解释     |
| matterId       | Integer       | 事项ID     |      | 需求ID             |
| projectId      | Integer       | 项目ID     |      |                  |
| title          | String        | 标题       |      |                  |
| priority       | Integer       | 优先级      |      |                  |
| storyPoint     | BigDecimal    | 故事点      |      |                  |
| startDate      | LocalDateTime | 开始日期     |      |                  |
| endDate        | LocalDateTime | 截止日期     |      |                  |
| completeDate   | LocalDateTime | 完成日期     |      |                  |
| storyType      |               | 缺陷类型     |      |                  |
| createBy       |               | 创建人      |      |                  |
| assignee       |               | 处理人      |      | 类型同`createBy`一样  |
| phase          |               | 阶段ID     |      | 类型同`storyType`一样 |
| status         |               | 状态ID     |      | 类型同`storyType`一样 |
| flowInstanceId | Integer       | 工作流ID    |      |                  |
| estimated      | BigDecimal    | 预估工时     |      |                  |
| progress       | Integer       | 进度       |      |                  |
| createTime     | LocalDateTime | 创建时间     |      |                  |
| updateTime     | LocalDateTime | 更新时间     |      |                  |
| nextStates     | List          | 可跳转的状态   |      |                  |
| nextStateIds   | List<Integer> | 可跳转的状态ID |      |                  |

### storyType

| 字段名  | 类型      | 描述 | 是否必填 | 备注           |
|------|---------|----|------|--------------|
| ...  |         |    |      | 其他字段不使用,暂不解释 |
| id   | Integer |    |      | 主键           |
| name | String  |    |      | 名称           |

### createBy

| 字段名          | 类型      | 描述 | 是否必填 | 备注           |
|--------------|---------|----|------|--------------|
| ...          |         |    |      | 其他字段不使用,暂不解释 |
| userId       | Integer |    |      | 用户ID         |
| userName     | String  |    |      | 用户名          |
| userRealName | String  |    |      | 用户名称         |
| email        | String  |    |      | 邮箱           |

### nextStates

| 字段名            | 类型      | 描述 | 是否必填 | 备注           |
|----------------|---------|----|------|--------------|
| ...            |         |    |      | 其他字段不使用,暂不解释 |
| processId      | Integer |    |      |              |
| stateFromId    | Integer |    |      |              |
| stateFromName  | String  |    |      | 当前项目状态       |
| stateFromPhase | Integer |    |      |              |
| stateToId      | Integer |    |      |              |
| stateToName    | String  |    |      |              |
| stateToPhase   | Integer |    |      |              |
| templateId     | Integer |    |      |              |
| name           | String  |    |      |              |
| isShow         | Integer |    |      |              |

### res.demo

```
{
	"success": true,
	"message": "操作成功",
	"code": 200,
	"timestamp": 1747121382927,
	"data": {
		"records": [
			{
				"matter_id": 682,
				"code": 13,
				"project_id": 270,
				"title": "【科技类】项目下->【devops项目】史诗下->创建->需求B",
				"priority": 2,
				"story_point": null,
				"start_date": "2025-04-16 00:00:00",
				"end_date": "2025-04-16 00:00:00",
				"complete_date": null,
				"bug_type": null,
				"story_type": {
					"id": 0,
					"name": "未分类"
				},
				"create_by": {
					"userId": 310000089,
					"userName": "liyuanqing",
					"userRealName": "liyuanqing",
					"email": "915226051@qq.com"
				},
				"assignee": {
					"userId": 310000089,
					"userName": "liyuanqing",
					"userRealName": "liyuanqing",
					"email": "915226051@qq.com"
				},
				"phase": {
					"id": 2,
					"name": "进行中"
				},
				"status": {
					"id": 39,
					"name": "研发中"
				},
				"matter_type": "STORY",
				"flow_instance_id": 2740,
				"estimated": null,
				"progress": 0,
				"create_time": "2025-05-06 16:39:48",
				"update_time": "2025-05-06 16:39:49",
				"next_states": [
					{
						"processId": 9283,
						"stateFromId": 39,
						"stateFromName": "研发中",
						"stateFromPhase": 2,
						"stateToId": 40,
						"stateToName": "已提测",
						"stateToPhase": 2,
						"templateId": 1543,
						"name": "创建提测单",
						"isShow": 1
					}
				],
				"next_state_ids": [
					40
				],
				"child": [],
				"version": {
					"id": 407,
					"name": "lyq-20250416"
				},
				"iteration": {
					"id": 0,
					"name": null
				},
				"epic": {
					"id": 380,
					"name": "devops项目"
				},
				"relevance_story_ids": null,
				"project": {
					"id": 270,
					"name": "DEVOPS-II"
				},
				"40": "信华信",
				"map": {
					"40": "信华信"
				}
			},
			{
				"matter_id": 677,
				"code": 2,
				"project_id": 270,
				"title": "【科技类】项目下->【devops项目】史诗下->创建->需求A",
				"priority": 2,
				"story_point": null,
				"start_date": "2025-04-16 00:00:00",
				"end_date": "2025-04-16 00:00:00",
				"complete_date": null,
				"bug_type": null,
				"story_type": {
					"id": 0,
					"name": "未分类"
				},
				"create_by": {
					"userId": 310000089,
					"userName": "liyuanqing",
					"userRealName": "liyuanqing",
					"email": "915226051@qq.com"
				},
				"assignee": {
					"userId": 310000089,
					"userName": "liyuanqing",
					"userRealName": "liyuanqing",
					"email": "915226051@qq.com"
				},
				"phase": {
					"id": 2,
					"name": "进行中"
				},
				"status": {
					"id": 40,
					"name": "已提测"
				},
				"matter_type": "STORY",
				"flow_instance_id": 2690,
				"estimated": null,
				"progress": 0,
				"create_time": "2025-04-17 15:06:04",
				"update_time": "2025-04-17 15:06:04",
				"next_states": [
					{
						"processId": 9284,
						"stateFromId": 40,
						"stateFromName": "已提测",
						"stateFromPhase": 2,
						"stateToId": 41,
						"stateToName": "测试完毕",
						"stateToPhase": 2,
						"templateId": 1543,
						"name": "UAT测试完成",
						"isShow": 1
					}
				],
				"next_state_ids": [
					41
				],
				"child": [
					{
						"matter_id": 201,
						"code": 6,
						"project_id": 270,
						"title": "AADAS",
						"priority": 2,
						"start_date": "2025-04-16 00:00:00",
						"end_date": "2025-04-16 00:00:00",
						"complete_date": null,
						"create_by": {
							"userId": 310000089,
							"userName": "liyuanqing",
							"userRealName": "liyuanqing",
							"email": "915226051@qq.com"
						},
						"assignee": {
							"userId": 310000089,
							"userName": "liyuanqing",
							"userRealName": "liyuanqing",
							"email": "915226051@qq.com"
						},
						"phase": {
							"id": 1,
							"name": "未开始"
						},
						"status": {
							"id": 3,
							"name": "开始"
						},
						"matter_type": "SUB_TASK",
						"flow_instance_id": 2695,
						"estimated": null,
						"progress": 0,
						"create_time": "2025-04-18 13:56:29",
						"update_time": "2025-04-18 13:56:29",
						"next_states": [
							{
								"processId": 9279,
								"stateFromId": 3,
								"stateFromName": "开始",
								"stateFromPhase": 1,
								"stateToId": 4,
								"stateToName": "进行中",
								"stateToPhase": 2,
								"templateId": 1542,
								"name": "进行中",
								"isShow": 1
							}
						],
						"next_state_ids": [
							4
						],
						"parent_id": 677,
						"parent_type": "STORY",
						"parent_code": 2
					},
					{
						"matter_id": 202,
						"code": 9,
						"project_id": 270,
						"title": "CADF",
						"priority": 2,
						"start_date": "2025-04-18 15:19:15",
						"end_date": null,
						"complete_date": null,
						"create_by": {
							"userId": 310000089,
							"userName": "liyuanqing",
							"userRealName": "liyuanqing",
							"email": "915226051@qq.com"
						},
						"assignee": {
							"userId": 310000089,
							"userName": "liyuanqing",
							"userRealName": "liyuanqing",
							"email": "915226051@qq.com"
						},
						"phase": {
							"id": 1,
							"name": "未开始"
						},
						"status": {
							"id": 3,
							"name": "开始"
						},
						"matter_type": "SUB_TASK",
						"flow_instance_id": 2698,
						"estimated": null,
						"progress": 0,
						"create_time": "2025-04-18 15:19:15",
						"update_time": "2025-04-18 15:19:15",
						"next_states": [
							{
								"processId": 9279,
								"stateFromId": 3,
								"stateFromName": "开始",
								"stateFromPhase": 1,
								"stateToId": 4,
								"stateToName": "进行中",
								"stateToPhase": 2,
								"templateId": 1542,
								"name": "进行中",
								"isShow": 1
							}
						],
						"next_state_ids": [
							4
						],
						"parent_id": 677,
						"parent_type": "STORY",
						"parent_code": 2
					},
					{
						"matter_id": 208,
						"code": 10,
						"project_id": 270,
						"title": "CAFFFF",
						"priority": 2,
						"start_date": "2025-04-22 15:34:48",
						"end_date": null,
						"complete_date": null,
						"create_by": {
							"userId": 310000089,
							"userName": "liyuanqing",
							"userRealName": "liyuanqing",
							"email": "915226051@qq.com"
						},
						"assignee": {
							"userId": 310000089,
							"userName": "liyuanqing",
							"userRealName": "liyuanqing",
							"email": "915226051@qq.com"
						},
						"phase": {
							"id": 1,
							"name": "未开始"
						},
						"status": {
							"id": 3,
							"name": "开始"
						},
						"matter_type": "SUB_TASK",
						"flow_instance_id": 2715,
						"estimated": null,
						"progress": 0,
						"create_time": "2025-04-22 15:34:48",
						"update_time": "2025-04-22 15:34:48",
						"next_states": [
							{
								"processId": 9279,
								"stateFromId": 3,
								"stateFromName": "开始",
								"stateFromPhase": 1,
								"stateToId": 4,
								"stateToName": "进行中",
								"stateToPhase": 2,
								"templateId": 1542,
								"name": "进行中",
								"isShow": 1
							}
						],
						"next_state_ids": [
							4
						],
						"parent_id": 677,
						"parent_type": "STORY",
						"parent_code": 2
					}
				],
				"version": {
					"id": 407,
					"name": "lyq-20250416"
				},
				"iteration": {
					"id": 0,
					"name": null
				},
				"epic": {
					"id": 380,
					"name": "devops项目"
				},
				"relevance_story_ids": null,
				"project": {
					"id": 270,
					"name": "DEVOPS-II"
				},
				"40": "信华信",
				"38": "A类型合同",
				"map": {
					"40": "信华信",
					"38": "A类型合同"
				}
			}
		],
		"total": 2,
		"size": 15,
		"current": 1,
		"orders": [
			{
				"column": "create_time",
				"asc": false
			}
		],
		"optimizeCountSql": true,
		"hitCount": false,
		"searchCount": true,
		"pages": 1
	}
}
```

# 根据项目创建BUG

## 说明

## 地址

/paas-web/devopssynergy/bug/add

## 请求方式

`POST`

## Header

```
Content-Type:application/json
refreshToken:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMjgwMjJ9.o4fDMDNZMdsVhbmv2I9mBaaM9I2t_BkUraVaMpU76z3rtnlPYR07K73Ltwp0W7fIaidfxGG3TzhiLT0miZVkE8Kf7il-lM6ZJFoHhQ7_IMOQdD9cCSFCbXkWzXTS79s_rZb9EC_e6NldhLz3vf7vRhXgq8ylmFTUz54Y0XK6cY4
token:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMzE2MjF9.cMcPXtQ3ov4Ks80Gc8FPl__G7j1DAlerIeMHX7b_AL8SQQNqxGGUmQebe4ycxVy3YmNjgzW7QHGoIfwBn42-GopSKeip27xz3a6GxeVFMt4n0WN5KZnivWUjBKF0Tmr8vBWdWjdYDJZG3bkENiXZA8-DUsMp3xPhASBBKn8Np7A
```

## req

| 字段名          | 类型                     | 描述      | 是否必填 | 备注                  |
|--------------|------------------------|---------|------|---------------------|
| ...          |                        |         |      | 其他字段不使用,暂不解释        |
| bugType      | Integer                | 缺陷类型    | 是    | `获取字典接口.bug_type`   |
| iterationId  | Integer                | 迭代id    |      | `欠缺迭代接口`            |
| versionId    | Integer                | 版本ID    |      | `获取版本接口`            |
| storyIds     | List<Integer> storyIds | 关联需求ID  | 是    | `创建BUG获取可关联需求接口`    |
| assignee     | Integer                | 处理人     | 是    | `获取当前项目下成员接口`       |
| estimated    | BigDecimal             | 预估工时    | 是    |                     |
| surplus      | BigDecimal             | 剩余工时    | 是    |                     |
| projectId    | Integer                | 项目ID    | 是    |                     |
| title        | String                 | 标题      | 是    | 标题不得超过200字符         |
| description  | String                 | 描述      | 是    | 描述（支持MarkDown)      |
| priority     | Integer                | 优先级     | 是    | 默认为2,欠缺字典           |
| startDate    | LocalDateTime          | 开始日期    | 是    | yyyy-MM-dd HH:mm:ss |
| endDate      | LocalDateTime          | 结束日期    | 是    | yyyy-MM-dd HH:mm:ss |
| source       | String                 | 来源      | 是    | 自动化测试平台/ms          |
| externalUser | String                 | 外部系统创建人 | 是    | 建议 唯一主键-姓名,不可超过255  |

### req.demo

```
{
	"projectId": "270",
	"title": "CAT",
	"description": "TTTT<img src=\"http://22.144.103.130:30901/bocloud/project/270/synergyannex/2025-05-13/622a4f13-3194-4d28-8e57-0aa7d9034c86.png\" />",
	"assignee": 310000089,
	"storyPoint": null,
	"priority": 2,
	"iterationId": "",
	"versionId": "",
	"startDate": "2025-05-13 00:00:00",
	"endDate": "2025-05-13 00:00:00",
	"annexIds": [],
	"estimated": 6,
	"epicId": "",
	"tagIds": [],
	"map": {},
	"storyIds": [
		677
	],
	"storyType": 0,
	"bugType": 0,
	"plantId": "",
	"caseId": "",
	"source": "ms",
	"externalUser": "李源青-10086",
}
```

## res

```
当前缺陷主键
"data": 188
```

```
{
    "success": true,
    "message": "操作成功",
    "code": 200,
    "timestamp": 1747121721964,
    "data": 188
}
```

## 获取版本接口

### 说明

`?projectId=270` 参数跟随具体项目走的。

### 地址

/paas-web/devopssynergy/version/undone-versions?projectId=270

### 请求方式

`GET`

### Header

```
refreshToken:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMjgwMjJ9.o4fDMDNZMdsVhbmv2I9mBaaM9I2t_BkUraVaMpU76z3rtnlPYR07K73Ltwp0W7fIaidfxGG3TzhiLT0miZVkE8Kf7il-lM6ZJFoHhQ7_IMOQdD9cCSFCbXkWzXTS79s_rZb9EC_e6NldhLz3vf7vRhXgq8ylmFTUz54Y0XK6cY4
token:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMzE2MjF9.cMcPXtQ3ov4Ks80Gc8FPl__G7j1DAlerIeMHX7b_AL8SQQNqxGGUmQebe4ycxVy3YmNjgzW7QHGoIfwBn42-GopSKeip27xz3a6GxeVFMt4n0WN5KZnivWUjBKF0Tmr8vBWdWjdYDJZG3bkENiXZA8-DUsMp3xPhASBBKn8Np7A
```

### req

### res

| 字段名  | 类型      | 描述   | 是否必填 | 备注           |
|------|---------|------|------|--------------|
| ...  |         |      |      | 其他字段不使用,暂不解释 |
| name | String  | 版本名称 |      |              |
| id   | Integer | 版本主键 |      |              |

### res.demo

```
{
    "success": true,
    "message": "操作成功",
    "code": 200,
    "timestamp": 1747189855113,
    "data": [
        {
            "id": 408,
            "name": "lyq-20250417",
            "versionKey": "lyq-20250417"
        },
        {
            "id": 407,
            "name": "lyq-20250416",
            "versionKey": "lyq-20250416"
        }
    ]
}
```

## 获取迭代接口

### 说明

`?projectId=270` 参数跟随具体项目走的。

### 地址

/paas-web/devopssynergy/iteration/undone-iterations?projectId=270

### 请求方式

`GET`

### Header

```
refreshToken:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMjgwMjJ9.o4fDMDNZMdsVhbmv2I9mBaaM9I2t_BkUraVaMpU76z3rtnlPYR07K73Ltwp0W7fIaidfxGG3TzhiLT0miZVkE8Kf7il-lM6ZJFoHhQ7_IMOQdD9cCSFCbXkWzXTS79s_rZb9EC_e6NldhLz3vf7vRhXgq8ylmFTUz54Y0XK6cY4
token:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMzE2MjF9.cMcPXtQ3ov4Ks80Gc8FPl__G7j1DAlerIeMHX7b_AL8SQQNqxGGUmQebe4ycxVy3YmNjgzW7QHGoIfwBn42-GopSKeip27xz3a6GxeVFMt4n0WN5KZnivWUjBKF0Tmr8vBWdWjdYDJZG3bkENiXZA8-DUsMp3xPhASBBKn8Np7A
```

### req

### res

| 字段名  | 类型      | 描述   | 是否必填 | 备注           |
|------|---------|------|------|--------------|
| ...  |         |      |      | 其他字段不使用,暂不解释 |
| name | String  | 迭代名称 |      |              |
| id   | Integer | 迭代主键 |      |              |

### res.demo

```
{
    "success": true,
    "message": "操作成功",
    "code": 200,
    "timestamp": 1747190180436,
    "data": [
        {
            "id": 33,
            "name": "迭代CCCC"
        }
    ]
}
```

## 创建BUG获取可关联需求接口

### 说明

`?projectId=270` 参数跟随具体项目走的。

### 地址

/paas-web/devopssynergy/story/listByProject?projectId=270

### 请求方式

`GET`

### Header

```
refreshToken:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMjgwMjJ9.o4fDMDNZMdsVhbmv2I9mBaaM9I2t_BkUraVaMpU76z3rtnlPYR07K73Ltwp0W7fIaidfxGG3TzhiLT0miZVkE8Kf7il-lM6ZJFoHhQ7_IMOQdD9cCSFCbXkWzXTS79s_rZb9EC_e6NldhLz3vf7vRhXgq8ylmFTUz54Y0XK6cY4
token:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMzE2MjF9.cMcPXtQ3ov4Ks80Gc8FPl__G7j1DAlerIeMHX7b_AL8SQQNqxGGUmQebe4ycxVy3YmNjgzW7QHGoIfwBn42-GopSKeip27xz3a6GxeVFMt4n0WN5KZnivWUjBKF0Tmr8vBWdWjdYDJZG3bkENiXZA8-DUsMp3xPhASBBKn8Np7A
```

### req

### res

| 字段名   | 类型      | 描述   | 是否必填 | 备注           |
|-------|---------|------|------|--------------|
| ...   |         |      |      | 其他字段不使用,暂不解释 |
| id    | Integer | 需求主键 |      |              |
| title | String  | 需求名称 |      |              |

### res.demo

```
{
    "success": true,
    "message": "操作成功",
    "code": 200,
    "timestamp": 1747190180435,
    "data": [
        {
            "id": 677,
            "code": 2,
            "projectId": 270,
            "title": "【科技类】项目下->【devops项目】史诗下->创建->需求A",
            "description": "",
            "createBy": 310000089,
            "assignee": 310000089,
            "storyPoint": null,
            "priority": 2,
            "iterationId": 0,
            "startDate": "2025-04-16 00:00:00",
            "endDate": "2025-04-16 00:00:00",
            "completeDate": null,
            "estimated": null,
            "storyType": 0,
            "flowInstanceId": 2690,
            "progress": 0,
            "isShow": true,
            "createTime": "2025-04-17 15:06:04",
            "updateTime": "2025-04-17 15:06:04",
            "isDelete": false
        },
        {
            "id": 682,
            "code": 13,
            "projectId": 270,
            "title": "【科技类】项目下->【devops项目】史诗下->创建->需求B",
            "description": "",
            "createBy": 310000089,
            "assignee": 310000089,
            "storyPoint": null,
            "priority": 2,
            "iterationId": 0,
            "startDate": "2025-04-16 00:00:00",
            "endDate": "2025-04-16 00:00:00",
            "completeDate": null,
            "estimated": null,
            "storyType": 0,
            "flowInstanceId": 2740,
            "progress": 0,
            "isShow": true,
            "createTime": "2025-05-06 16:39:48",
            "updateTime": "2025-05-06 16:39:49",
            "isDelete": false
        }
    ]
}
```

## 获取字典接口

### 说明

### 地址

/paas-web/devopssynergy/dictionary/findByType

### 请求方式

`GET`

### Header

```
refreshToken:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMjgwMjJ9.o4fDMDNZMdsVhbmv2I9mBaaM9I2t_BkUraVaMpU76z3rtnlPYR07K73Ltwp0W7fIaidfxGG3TzhiLT0miZVkE8Kf7il-lM6ZJFoHhQ7_IMOQdD9cCSFCbXkWzXTS79s_rZb9EC_e6NldhLz3vf7vRhXgq8ylmFTUz54Y0XK6cY4
token:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMzE2MjF9.cMcPXtQ3ov4Ks80Gc8FPl__G7j1DAlerIeMHX7b_AL8SQQNqxGGUmQebe4ycxVy3YmNjgzW7QHGoIfwBn42-GopSKeip27xz3a6GxeVFMt4n0WN5KZnivWUjBKF0Tmr8vBWdWjdYDJZG3bkENiXZA8-DUsMp3xPhASBBKn8Np7A
```

### req

### res

| 字段名         | 类型 | 描述        | 是否必填 | 备注           |
|-------------|----|-----------|------|--------------|
| ...         |    |           |      | 其他字段不使用,暂不解释 |
| bug_type    | 字典 | 缺陷类型      |      |              |
| filter_type | 字典 | 事项筛选类型-全部 |      |              |
| priority    | 字典 | 优先级       |      |              |

#### 字典

| 字段名       | 类型      | 描述   | 是否必填 | 备注           |
|-----------|---------|------|------|--------------|
| ...       |         |      |      | 其他字段不使用,暂不解释 |
| dictId    | Integer | 字典主键 |      |              |
| dictType  | String  | 字典类型 |      |              |
| dictValue | String  | 字典名称 |      |              |

### res.demo

```
{
    "success": true,
    "message": "操作成功",
    "code": 200,
    "timestamp": 1747190180547,
    "data": [
        {
            "bug_type": [
                {
                    "dictId": 13,
                    "dictType": "bug_type",
                    "dictValue": "未分类",
                    "dictKey": 0,
                    "delFlag": false,
                    "remark": "缺陷类型-未分类"
                },
                {
                    "dictId": 14,
                    "dictType": "bug_type",
                    "dictValue": "UI界面问题",
                    "dictKey": 1,
                    "delFlag": false,
                    "remark": "缺陷类型-UI界面问题"
                },
                {
                    "dictId": 15,
                    "dictType": "bug_type",
                    "dictValue": "易用性问题",
                    "dictKey": 2,
                    "delFlag": false,
                    "remark": "缺陷类型-易用性问题"
                },
                {
                    "dictId": 16,
                    "dictType": "bug_type",
                    "dictValue": "安全问题",
                    "dictKey": 3,
                    "delFlag": false,
                    "remark": "缺陷类型-安全问题"
                },
                {
                    "dictId": 17,
                    "dictType": "bug_type",
                    "dictValue": "性能问题",
                    "dictKey": 4,
                    "delFlag": false,
                    "remark": "缺陷类型-性能问题"
                },
                {
                    "dictId": 18,
                    "dictType": "bug_type",
                    "dictValue": "功能问题",
                    "dictKey": 5,
                    "delFlag": false,
                    "remark": "缺陷类型-功能问题"
                }
            ],
            "priority": [
                {
                    "dictId": 5,
                    "dictType": "priority",
                    "dictValue": "低",
                    "dictKey": 1,
                    "delFlag": false,
                    "remark": "优先级-低"
                },
                {
                    "dictId": 6,
                    "dictType": "priority",
                    "dictValue": "中",
                    "dictKey": 2,
                    "delFlag": false,
                    "remark": "优先级-中"
                },
                {
                    "dictId": 7,
                    "dictType": "priority",
                    "dictValue": "高",
                    "dictKey": 3,
                    "delFlag": false,
                    "remark": "优先级-高"
                },
                {
                    "dictId": 8,
                    "dictType": "priority",
                    "dictValue": "紧急",
                    "dictKey": 4,
                    "delFlag": false,
                    "remark": "优先级-紧急"
                }
            ],
            "mattey_type": [
                {
                    "dictId": 1,
                    "dictType": "mattey_type",
                    "dictValue": "需求",
                    "dictKey": 1,
                    "delFlag": false,
                    "remark": "事项类型-需求"
                },
                {
                    "dictId": 2,
                    "dictType": "mattey_type",
                    "dictValue": "任务",
                    "dictKey": 2,
                    "delFlag": false,
                    "remark": "事项类型-任务"
                },
                {
                    "dictId": 3,
                    "dictType": "mattey_type",
                    "dictValue": "缺陷",
                    "dictKey": 3,
                    "delFlag": false,
                    "remark": "事项类型-缺陷"
                },
                {
                    "dictId": 4,
                    "dictType": "mattey_type",
                    "dictValue": "子任务",
                    "dictKey": 4,
                    "delFlag": false,
                    "remark": "事项类型-子任务"
                },
                {
                    "dictId": 19,
                    "dictType": "mattey_type",
                    "dictValue": "版本",
                    "dictKey": 5,
                    "delFlag": false,
                    "remark": "事项类型-版本"
                },
                {
                    "dictId": 28,
                    "dictType": "mattey_type",
                    "dictValue": "史诗",
                    "dictKey": 5,
                    "delFlag": false,
                    "remark": "事项类型-史诗"
                }
            ],
            "story_type": [
                {
                    "dictId": 9,
                    "dictType": "story_type",
                    "dictValue": "未分类",
                    "dictKey": 0,
                    "delFlag": false,
                    "remark": "需求类型-未分类"
                },
                {
                    "dictId": 10,
                    "dictType": "story_type",
                    "dictValue": "产品需求",
                    "dictKey": 1,
                    "delFlag": false,
                    "remark": "需求类型-产品需求"
                },
                {
                    "dictId": 11,
                    "dictType": "story_type",
                    "dictValue": "技术需求",
                    "dictKey": 2,
                    "delFlag": false,
                    "remark": "需求类型-技术需求"
                },
                {
                    "dictId": 12,
                    "dictType": "story_type",
                    "dictValue": "运营需求",
                    "dictKey": 3,
                    "delFlag": false,
                    "remark": "需求类型-运营需求"
                }
            ],
            "filter_type": [
                {
                    "dictId": 20,
                    "dictType": "filter_type",
                    "dictValue": "全部",
                    "dictKey": 1,
                    "delFlag": false,
                    "remark": "事项筛选类型-全部"
                },
                {
                    "dictId": 21,
                    "dictType": "filter_type",
                    "dictValue": "全部打开的",
                    "dictKey": 2,
                    "delFlag": false,
                    "remark": "事项筛选类型-全部打开的"
                },
                {
                    "dictId": 22,
                    "dictType": "filter_type",
                    "dictValue": "我创建的",
                    "dictKey": 3,
                    "delFlag": false,
                    "remark": "事项筛选类型-我创建的"
                },
                {
                    "dictId": 23,
                    "dictType": "filter_type",
                    "dictValue": "分配给我的",
                    "dictKey": 4,
                    "delFlag": false,
                    "remark": "事项筛选类型-分配给我的"
                },
                {
                    "dictId": 24,
                    "dictType": "filter_type",
                    "dictValue": "我未完成的",
                    "dictKey": 5,
                    "delFlag": false,
                    "remark": "事项筛选类型-我未完成的"
                }
            ]
        }
    ]
}
```

## 获取当前项目下成员接口

### 说明

### 地址

/paas-web/devopsplatform/projectUser/projectMembers?projectId=270

### 请求方式

`GET`

### Header

```
refreshToken:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMjgwMjJ9.o4fDMDNZMdsVhbmv2I9mBaaM9I2t_BkUraVaMpU76z3rtnlPYR07K73Ltwp0W7fIaidfxGG3TzhiLT0miZVkE8Kf7il-lM6ZJFoHhQ7_IMOQdD9cCSFCbXkWzXTS79s_rZb9EC_e6NldhLz3vf7vRhXgq8ylmFTUz54Y0XK6cY4
token:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMzE2MjF9.cMcPXtQ3ov4Ks80Gc8FPl__G7j1DAlerIeMHX7b_AL8SQQNqxGGUmQebe4ycxVy3YmNjgzW7QHGoIfwBn42-GopSKeip27xz3a6GxeVFMt4n0WN5KZnivWUjBKF0Tmr8vBWdWjdYDJZG3bkENiXZA8-DUsMp3xPhASBBKn8Np7A
```

### req

### res

| 字段名  | 类型      | 描述   | 是否必填 | 备注           |
|------|---------|------|------|--------------|
| ...  |         |      |      | 其他字段不使用,暂不解释 |
| id   | Integer | 成员主键 |      |              |
| name | String  | 成员名称 |      |              |

#### 字典

### res.demo

```
{
    "success": true,
    "message": "操作成功",
    "code": 200,
    "timestamp": 1747191254201,
    "data": [
        {
            "id": 310000089,
            "createTime": "2025-04-01 15:10:26",
            "updateTime": "2025-05-14 09:35:02",
            "name": "liyuanqing",
            "login": "liyuanqing",
            "password": "E64B78FC3BC91BCBC7DC232BA8EC59E0",
            "state": 0,
            "accountType": 0,
            "phone": "18888888884",
            "email": "915226051@qq.com",
            "company": "",
            "source": 0,
            "sourceId": "",
            "firstLogin": 1,
            "errorCount": 0,
            "lastTime": "2025-05-14 09:35:02",
            "ldapOrgDn": "",
            "sourceConfigId": 0,
            "admissionDate": "20250401",
            "accountLevel": "senior",
            "accountRole": "develop",
            "isReceiveEmail": true
        }
    ]
}
```

# 修改BUG

## 说明

## 地址

/paas-web/devopssynergy/bug/edit

## 请求方式

`PUT`

## Header

```
Content-Type:application/json
refreshToken:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMjgwMjJ9.o4fDMDNZMdsVhbmv2I9mBaaM9I2t_BkUraVaMpU76z3rtnlPYR07K73Ltwp0W7fIaidfxGG3TzhiLT0miZVkE8Kf7il-lM6ZJFoHhQ7_IMOQdD9cCSFCbXkWzXTS79s_rZb9EC_e6NldhLz3vf7vRhXgq8ylmFTUz54Y0XK6cY4
token:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMzE2MjF9.cMcPXtQ3ov4Ks80Gc8FPl__G7j1DAlerIeMHX7b_AL8SQQNqxGGUmQebe4ycxVy3YmNjgzW7QHGoIfwBn42-GopSKeip27xz3a6GxeVFMt4n0WN5KZnivWUjBKF0Tmr8vBWdWjdYDJZG3bkENiXZA8-DUsMp3xPhASBBKn8Np7A
```

## req

| 字段名         | 类型            | 描述              | 是否必填 | 备注                  |
|-------------|---------------|-----------------|------|---------------------|
| ...         |               |                 |      | 其他字段不使用,暂不解释        |
| bugType     | Integer       | 缺陷类型            |      |                     |
| iterationId | Integer       | 迭代id            |      |                     |
| versionId   | Integer       | 版本ID            |      |                     |
| storyIds    | List<Integer> | 需求ID            |      |                     |
| title       | String        | 标题              |      |                     |
| title       | String        | 标题              | 是    | 标题不得超过200字符         |
| assignee    | Integer       | 处理人             | 是    |                     |
| description | String        | 描述              | 是    | 描述（支持MarkDown)      |
| priority    | Integer       | 优先级             | 是    |                     |
| startDate   | LocalDateTime | 开始日期            | 是    | yyyy-MM-dd HH:mm:ss |
| endDate     | LocalDateTime | 结束日期            | 是    | yyyy-MM-dd HH:mm:ss |
| storyPoint  | BigDecimal    | 故事点             |      |                     |
| stateId     | Integer       | 状态ID，更新工作项的时候使用 |      |                     |

```
{
	"id": 183,
	"title": "测试创建缺陷",
	"description": "<img src=\"http://22.144.103.130:30901/bocloud/project/270/synergyannex/2025-05-13/54032ecd-f1f4-4e02-a445-de06659f2ffd.png\" /><img src=\"http://22.144.103.130:30901/bocloud/project/270/synergyannex/2025-05-13/f7881ea4-fd51-4ed2-b136-2b84bf7be7f4.png\" />",
	"assignee": 310000089,
	"iterationId": 0,
	"priority": 2,
	"storyPoint": null,
	"startDate": "2025-05-13 00:00:00",
	"endDate": "2025-05-13 00:00:00",
	"bugType": 1,
	"annexIds": [],
	"projectId": "270",
	"storyType": "",
	"estimated": 6,
	"surplus": 6,
	"progress": 0,
	"versionId": null,
	"storyIds": [
		677
	],
	"stateId": 33,
	"map": {}
}
```

## res

```
{
    "success": true,
    "message": "操作成功",
    "code": 200,
    "timestamp": 1747121857377,
    "data": null
}
```

# 获取BUG可调整的工作流状态

## 说明

## 地址

/paas-web/devopssynergy/bug/detail/183

## 请求方式

`GET`

## Header

```
refreshToken:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMjgwMjJ9.o4fDMDNZMdsVhbmv2I9mBaaM9I2t_BkUraVaMpU76z3rtnlPYR07K73Ltwp0W7fIaidfxGG3TzhiLT0miZVkE8Kf7il-lM6ZJFoHhQ7_IMOQdD9cCSFCbXkWzXTS79s_rZb9EC_e6NldhLz3vf7vRhXgq8ylmFTUz54Y0XK6cY4
token:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdXRvcGxhdGZvcm0iLCJhY2NvdW50SWQiOiIzMTAwMDAxMDUiLCJuYW1lIjoi6Ieq5Yqo5YyW5bmz5Y-wIiwic2Vzc2lvbklkIjoiMjQ5NDZmNGNmMjg3NDJmOTk4MTk3OTUzNWFjM2FiZmIiLCJleHAiOjE3NDcxMzE2MjF9.cMcPXtQ3ov4Ks80Gc8FPl__G7j1DAlerIeMHX7b_AL8SQQNqxGGUmQebe4ycxVy3YmNjgzW7QHGoIfwBn42-GopSKeip27xz3a6GxeVFMt4n0WN5KZnivWUjBKF0Tmr8vBWdWjdYDJZG3bkENiXZA8-DUsMp3xPhASBBKn8Np7A
```

## req

## res

| 字段名               | 类型            | 描述                  | 是否必填 | 备注           |
|-------------------|---------------|---------------------|------|--------------|
| ...               |               |                     |      | 其他字段不使用,暂不解释 |
| createUserName    | String        | 创建人名                |      |              |
| assigneeUserName  | String        | 处理人名                |      |              |
| versionId         | Integer       | 版本ID                |      |              |
| versionName       | String        | 版本名称                |      |              |
| nextStates        | List          | 可选状态                |      |              |
| relevanceStoryIds | List<Integer> | 绑定的需求id             |      |              |
| id                | Integer       | 事项id/缺陷ID           |      |              |
| code              | Integer       | 事项引用id              |      |              |
| projectId         | Integer       | 项目ID                |      |              |
| title             | String        | 标题                  |      |              |
| description       | String        | 描述（支持MarkDown)      |      |              |
| storyPoint        | String        | 故事点                 |      |              |
| priority          | Integer       | 优先级                 |      |              |
| iterationId       | Integer       | 迭代id                |      |              |
| iterationTitle    | String        | 迭代标题                |      |              |
| startDate         | LocalDateTime | 开始日期                |      |              |
| endDate           | LocalDateTime | 截止日期                |      |              |
| completeDate      | LocalDateTime | 完成日期                |      |              |
| bugType           | Integer       | 缺陷类型                |      |              |
| progress          | Integer       | 进度                  |      |              |
| createTime        | LocalDateTime | 创建时间                |      |              |
| stateId           | Integer       | 状态ID                |      |              |
| stateName         | String        | 状态名称                |      |              |
| versionId         | Integer       | 版本ID                |      |              |
| source            | String        | 来源                  |      |              |
| externalUser      | String        | 外部系统创建人(建议 唯一主键-姓名) |      |              |

### nextStates

| 字段名            | 类型      | 描述 | 是否必填 | 备注           |
|----------------|---------|----|------|--------------|
| ...            |         |    |      | 其他字段不使用,暂不解释 |
| processId      | Integer |    |      |              |
| stateFromId    | Integer |    |      |              |
| stateFromName  | String  |    |      | 当前项目状态       |
| stateFromPhase | Integer |    |      |              |
| stateToId      | Integer |    |      |              |
| stateToName    | String  |    |      |              |
| stateToPhase   | Integer |    |      |              |
| templateId     | Integer |    |      |              |
| name           | String  |    |      |              |
| isShow         | Integer |    |      |              |

```
{
    "success": true,
    "message": "操作成功",
    "code": 200,
    "timestamp": 1747121976715,
    "data": {
        "id": 183,
        "code": 15,
        "projectId": 270,
        "type": "BUG",
        "title": "测试创建缺陷",
        "description": "<img src=\"http://22.144.103.130:30901/bocloud/project/270/synergyannex/2025-05-13/54032ecd-f1f4-4e02-a445-de06659f2ffd.png\" /><img src=\"http://22.144.103.130:30901/bocloud/project/270/synergyannex/2025-05-13/f7881ea4-fd51-4ed2-b136-2b84bf7be7f4.png\" />",
        "createBy": 310000089,
        "assignee": 310000089,
        "storyPoint": null,
        "priority": 2,
        "iterationId": 0,
        "iterationTitle": null,
        "startDate": "2025-05-13 00:00:00",
        "endDate": "2025-05-13 00:00:00",
        "completeDate": null,
        "bugType": 1,
        "flowInstanceId": 2796,
        "progress": 0,
        "createTime": "2025-05-13 14:51:47",
        "updateTime": "2025-05-13 15:37:37",
        "stateId": 33,
        "stateName": "打开",
        "versionId": 0,
        "subTaskTotal": null,
        "subTaskCompleted": null,
        "estimated": 6.00,
        "surplus": 6.00,
        "createUserName": "liyuanqing",
        "assigneeUserName": "liyuanqing",
        "versionName": "未规划",
        "child": null,
        "annexes": [],
        "nextStates": [
            {
                "processId": 9287,
                "stateFromId": 33,
                "stateFromName": "打开",
                "stateFromPhase": 2,
                "stateToId": 4,
                "stateToName": "进行中",
                "stateToPhase": 2,
                "templateId": 1544,
                "name": "进行中",
                "isShow": 1
            },
            {
                "processId": 9290,
                "stateFromId": 33,
                "stateFromName": "打开",
                "stateFromPhase": 2,
                "stateToId": 37,
                "stateToName": "无效",
                "stateToPhase": 3,
                "templateId": 1544,
                "name": "无效",
                "isShow": 1
            }
        ],
        "nextStateIds": [],
        "map": {},
        "relevanceStoryIds": [
            677
        ],
        "enableDelivery": true,
        "branchVOList": null
    }
}
```


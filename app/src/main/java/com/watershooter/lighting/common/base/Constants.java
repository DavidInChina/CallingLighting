package com.watershooter.lighting.common.base;

/**
 * Created by davidinchina on 16/9/30.
 * <p>
 * 常量定义
 */

public class Constants {

    /**
     * 应用 序主目录
     */
    public final static String ROOT_DIRECTORY = "telrecord";

    /**
     * 应用 序的图片 存目录
     */
    public final static String IMAGE_CACHE_DIRECTORY = ROOT_DIRECTORY + "/ImageCache";

    /**
     * 应用 序上传文件 存目录
     */
    public final static String UPLOAD_UPLOAD_DIRECTORY = ROOT_DIRECTORY + "/Upload";


    /**
     * 应用 序下载文件 存目录
     */
    public final static String UPLOAD_DOWNLOAD_DIRECTORY = ROOT_DIRECTORY + "/Download";
    /**
     * 应用 序下载文件 存目录
     */
    public final static String IMG_DOWNLOAD_DIRECTORY = ROOT_DIRECTORY + "/ImgDownload";
    /**
     * 应用 序异步报告内容本地 存
     */
    public final static String REPORT_SYNC_DIRECTORY = ROOT_DIRECTORY + "/REPORTSync";

    /**
     * 用户帖子文件保存名称
     */
    public final static String REPORT_INFO_FILE_NAME = "reportinfo";
    /**
     * 用户本地通讯录文件保存名称
     */
    public final static String ADDRESS_INFO_FILE_NAME = "addressinfo";
    /**
     * 应用程序录音文件
     */
    public final static String REPORT_AUDIO_DIRECTORY = ROOT_DIRECTORY + "/AudioRecord";

    /**
     * 数据库名
     */
    public final static String DB_NAME = "telrec";

    /**
     * 用户资料文件保存名
     */
    public final static String USER_INFO_FILE_NAME = "userinfo";
    /**
     * 通信请求返回列表的总长度
     */
    public final static String SUCCESS_TOTALSIZE_KEY = "totalSize";
    /**
     * 通信请求返回的数据集
     */
    public final static String SUCCESS_DATA_KEY = "data";
    /**
     * 通信请求返回的支付凭证
     */
    public final static String SUCCESS_CHARGE_KEY = "charge";
    /**
     * 文件上传后返回的文件路径
     */
    public final static String SUCCESS_PATH_KEY = "filePath";

    /**
     * 通信请求状态码在api数据中的code
     */
    public final static String SUCCESS_CODE_KEY = "code";
    /**
     * 通信请求返回成功在api数据中的对应{@link #SUCCESS_CODE_KEY}的值
     */
    public final static int SUCCESS_CODE_VALUE = 0;
    /**
     * 应用需要更新code
     */
    public final static int update_CODE_VALUE = 125;
    /**
     * 通信请求返回信息在api数据中的key，错误信息描述
     */
    public final static String REQUEST_MSG_KEY = "msg";


    /**
     * 服务器接口地址
     */
    public final static String URL = "http://121.199.9.156:8080/api/";//正式服务器
//    public final static String URL = "http://121.199.9.156:8080/";//测试服务器2
//    public final static String URL = "http://192.168.1.29:8080/";//搬豆wifi
//        public final static String URL = "http://172.20.10.2:8080/";//本机

    /**
     * 发送验证码地址
     */
    public final static String ALL_SEND_VERIFY_URL = URL + "all/sendVerify";
    /**
     * 验证验证码地址
     */
    public final static String ALL_CHECK_VERIFY_URL = URL + "all/checkVerify";
    /**
     * 用户注册接口地址
     */
    public final static String USER_REGISTER_URL = URL + "user/register";
    /**
     * 用户充值密码接口地址
     */
    public final static String USER_RESET_URL = URL + "user/findPassword";
    /**
     * 用户登录接口地址
     */
    public final static String USER_LOGIN_URL = URL + "user/login";
    /**
     * 用户信息获取接口地址
     */
    public final static String USER_INFO_URL = URL + "user/information";

    /**
     * 创建公司接口地址
     */
    public final static String CMP_CREATE_URL = URL + "company/createCmp";
    /**
     * 修改公司信息接口地址
     */
    public final static String CMP_UPDATE_URL = URL + "company/modifyCompany";
    /**
     * 文件上传接口地址
     */
    public final static String FILE_UPLOAD_URL = URL + "all/upload";
    /**
     * 部门列表接口地址
     */
    public final static String CMP_DEPT_LIST_URL = URL + "company/deptList";
    /**
     * 添加部门地址
     */
    public final static String CREATE_DEPT_URL = URL + "company/saveDept";
    /**
     * 获取部门信息地址
     */
    public final static String DEPT_INFO_URL = URL + "company/deptInfo";
    /**
     * 获取部门信息修改地址
     */
    public final static String UPDATE_DEPT_INFO_URL = URL + "company/modifyDept";
    /**
     * 获取添加员工地址
     */
    public final static String EMP_ADD_URL = URL + "company/saveStaff";
    /**
     * 获取员工信息地址
     */
    public final static String EMP_INFO_URL = URL + "company/staffInfo";
    /**
     * 获取添加员工地址
     */
    public final static String EMP_LIST_URL = URL + "company/staffList";
    /**
     * 批量修改员工信息地址
     */
    public final static String MODIFY_EMP_LIST_URL = URL + "company/modifiyStaffIdentify";
    /**
     * 获取通讯录列表地址
     */
    public final static String ADDRESS_LIST_URL = URL + "addressList/addressListList";
    /**
     * 检查应用更新接口
     */
    public final static String CHECK_UPDATE_URL = URL + "all/checkApp";
    /**
     * 获取添加通讯录地址
     */
    public final static String ADDRESS_ADD_URL = URL + "addressList/saveAddressList";
    /**
     * 修改通话记录
     */
    public final static String RECORD_MODIFY_URL = URL + "public/recordModify";
    /**
     * 删除通话记录
     */
    public final static String RECORD_RESUME_URL = URL + "public/resumedRecord";
    /**
     * 恢复通话记录
     */
    public final static String RECORD_DELETE_URL = URL + "public/recordDelete";
    /**
     * 获取客户信息地址
     */
    public final static String ADDRESS_GET_CLIENT_URL = URL + "addressList/addressDetail";
    /**
     * 获取订单详情地址
     */
    public final static String ORDER_DETAIL_URL = URL + "order/orderDetail";
    /**
     * 修改客户信息地址
     */
    public final static String ADDRESS_UPDATET_URL = URL + "addressList/modifyAddressListInfo";
    /**
     * 删除通讯录信息地址
     */
    public final static String ADDRESS_REMOVE_URL = URL + "addressList/deleteAddressList";
    /**
     * 恢复通讯录信息地址
     */
    public final static String ADDRESS_RESUME_URL = URL + "addressList/resumedAddressList";
    /**
     * 通讯录信息历史记录列表
     */
    public final static String ADDRESS_OLD_LIST_URL = URL + "addressList/addressListInfoList";
    /**
     * vip商品列表
     */
    public final static String VIP_GOODS_LIST_URL = URL + "vipgoods/ vipList";
    /**
     * vip下单
     */
    public final static String VIP_GOODS_BUY_URL = URL + "order/vipBuy";
    /**
     * vip订单支付
     */
    public final static String VIP_ORDER_PAY_URL = URL + "order/charge";
    /**
     * 我的订单列表
     */
    public final static String VIP_ORDER_LIST_URL = URL + "order/orderList";
    /**
     * 订单删除列表
     */
    public final static String VIP_ORDER_DELETE_URL = URL + "order/orderDelete";
    /**
     * 报告列表
     */
    public final static String REPORT_LIST_URL = URL + "userpaper/userPaperList";
    /**
     * 添加报表接口
     */
    public final static String REPORT_ADD_URL = URL + "userpaper/addUserPaper";
    /**
     * 修改报表接口
     */
    public final static String REPORT_UPDATE_URL = URL + "userpaper/addUserPaper";
    /**
     * 报告详情接口
     */
    public final static String REPORT_DETAIL_URL = URL + "userpaper/userPaperInfo";
    /**
     * 公司每日数据查看接口
     */
    public final static String DATA_COUNT_URL = URL + "data/dataDayLook";
    /**
     * 公司信息查看接口
     */
    public final static String CMP_INFO_URL = URL + "company/getCompanyInfo";
    /**
     * 首页记录列表查看接口
     */
    public final static String RECORD_ALL_LIST_URL = URL + "public/allList";
    /**
     * 转移联系人接口
     */
    public final static String ADDRESS_CHANGE_URL = URL + "addressList/changeAddressList";
    /**
     * 用户修改密码接口
     */
    public final static String USER_MODIFY_PS_URL = URL + "user/modifyPassword";
    /**
     * 员工密码找回接口
     */
    public final static String EMP_MODIFY_PS_URL = URL + "company/findStaffPassword";
    /**
     * 修改员工信息接口
     */
    public final static String EMP_MODIFY_INFO_URL = URL + "company/modifiyStaff";
    /**
     * 公司领导获取账号列表
     */
    public final static String BOSS_ACCOUNT_URL = URL + "user/findAccount";
    /**
     * 获取通话记录的备注列表
     */
    public final static String RECORD_COMMENT_URL = URL + "public/recordTipList";
    /**
     * 获取添加备注
     */
    public final static String RECORD_COMMENT_ADD_URL = URL + "public/recordTipAdd";
    /**
     * 根据联系人电话获取通讯录详情
     */
    public final static String ADDRESS_INFOBYPHONE_URL = URL + "addressList/addressInfoByPhone";
    /**
     * 获取通话记录详情
     */
    public final static String RECORD_DETAIL_URL = URL + "public/recordDetail";
    /**
     * 修改个人信息
     */
    public final static String USER_INFO_MODIFY_URL = URL + "user/modifyInfo";
    /**
     * 删除报告
     */
    public final static String REPORT_DELETE_URL = URL + "userpaper/userPaperDelete";
    /**
     * 删除员工
     */
    public final static String EMP_DELETE_URL = URL + "company/deleteStaff";
    /**
     * 恢复员工
     */
    public final static String EMP_RESUME_URL = URL + "company/resumedStaff";
    /**
     * 删除部门
     */
    public final static String DEPT_DELETE_URL = URL + "company/deleteDept";
    /**
     * 添加通话记录
     */
    public final static String RECORD_ADD_URL = URL + "public/addRecord";
    /**
     * 修改通话记录
     */
    public final static String RECORD_UPDATE_URL = URL + "public/recordModify";
    /**
     * 消息未读数
     */
    public final static String MSG_UNRED_COUNT_URL = URL + "msg/msgUnreadCount";
    /**
     * 消息列表
     */
    public final static String MSG_LIST_URL = URL + "msg/messageList";
    /**
     * 已读消息
     */
    public final static String READMSG_URL = URL + "msg/msgRead";
    /**
     * 删除消息
     */
    public final static String DELDMSG_URL = URL + "msg/msgDelete";
    /**
     * 公司组织结构
     */
    public final static String CMPSTRUCT_URL = URL + "company/structure";

}

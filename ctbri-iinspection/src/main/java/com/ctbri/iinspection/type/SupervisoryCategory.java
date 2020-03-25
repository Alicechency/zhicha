package com.ctbri.iinspection.type;

import java.util.HashMap;
import java.util.Map;

import com.ctbri.common.type.Type;

public enum SupervisoryCategory implements Type {

	/**
	 * 盗窃案
	 */
	DQA(1, "盗窃案"),
	/**
	 * 文娱场所从业人员
	 */
	WYCSCYRY(2, "文娱场所从业人员"),
	/**
	 * 故意伤害案
	 */
	GYSHA(3, "故意伤害案"),
	/**
	 * 吸毒人员
	 */
	XDRY(4, "吸毒人员"),
	/**
	 * 赌博前科人员
	 */
	DBQKRY(5, "赌博前科人员"),
	/**
	 * 抢劫案
	 */
	QJIEA(6, "抢劫案"),
	/**
	 * 诈骗案
	 */
	ZPA(7, "诈骗案"),
	/**
	 * 钻窗入室盗窃案件人员
	 */
	ZCRSDQANRY(8, "钻窗入室盗窃案件人员"),
	/**
	 * 拦路抢劫案件人员
	 */
	LLQJRY(9, "拦路抢劫案件人员"),
	/**
	 * 聚众斗殴案
	 */
	JZDOA(10, "聚众斗殴案"),
	/**
	 * 强奸案
	 */
	QJIANA(11, "强奸案"),
	/**
	 * 制贩毒人员
	 */
	ZFDRY(12, "制贩毒人员"),
	/**
	 * 敲诈勒索案
	 */
	QZLSA(13, "敲诈勒索案"),
	/**
	 * 非法拘禁案
	 */
	FFJJA(14, "非法拘禁案"),
	/**
	 * 卖淫前科人员
	 */
	MYQKRY(15, "卖淫前科人员"),
	/**
	 * 有精神病背景重点人员
	 */
	YJSBBJZDRY(16, "有精神病背景重点人员"),
	/**
	 * 信用卡诈骗案
	 */
	XYKZPA(17, "信用卡诈骗案"),
	/**
	 * 溜门入室盗窃案件人员
	 */
	LMRSDQAJRY(18, "溜门入室盗窃案件人员"),
	/**
	 * 抢夺案
	 */
	QDA(19, "抢夺案"),
	/**
	 * 街头诈骗案件
	 */
	JTZPAJ(20, "街头诈骗案件"),
	/**
	 * 合同诈骗案
	 */
	HTZPA(21, "合同诈骗案"),
	/**
	 * 入室盗窃案
	 */
	RSQJA(22, "入室盗窃案"),
	/**
	 * 以危险方法危害公共安全案
	 */
	YWHFFWHGGAQA(23, "以危险方法危害公共安全案"),
	/**
	 * 伪造，变造公文，证件，印章案
	 */
	WZBZGWZJYZA(24, "伪造，变造公文，证件，印章案"),
	/**
	 * 肇事肇祸精神病人
	 */
	ZSZHJSBR(25, "肇事肇祸精神病人"),
	/**
	 * 入室抢劫案件
	 */
	RSQJAJ(26, "入室抢劫案件"),
	/**
	 * 有潜在暴力倾向精神病人
	 */
	YQZBLQXJSBR(27, "有潜在暴力倾向精神病人"),
	/**
	 * 绑架案
	 */
	BJA(28, "绑架案"),
	/**
	 * 强制猥亵，侮辱妇女案
	 */
	QZWXWRFNA(29, "强制猥亵，侮辱妇女案"),
	/**
	 * 撬门入室盗窃案件人员
	 */
	QMRSDQAJRY(30, "撬门入室盗窃案件人员"),
	/**
	 * 在逃人员
	 */
	ZTRY(31, "在逃人员"),
	/**
	 * 治安重点上访人员
	 */
	ZAZDSFRY(32, "治安重点上访人员"),
	/**
	 * 非法吸收公众存款案
	 */
	FFXSGZCKA(33, "非法吸收公众存款案"),
	/**
	 * 轻微滋事精神病人
	 */
	QWZSJSBR(34, "轻微滋事精神病人"),
	/**
	 * 以其他危险方法危害公共安全案
	 */
	YQTWXFFWHGGAQA(35, "以其他危险方法危害公共安全案"),
	/**
	 * 票据诈骗案
	 */
	PJZPA(36, "票据诈骗案"),
	/**
	 * 涉枪前科人员
	 */
	SQQKRY(37, "涉枪前科人员"),
	/**
	 * 招摇撞骗案
	 */
	ZYZPA(38, "招摇撞骗案"),
	/**
	 * 买卖公文证件印章案
	 */
	MMGWZJYZA(39, "买卖公文证件印章案"),
	/**
	 * 贷款诈骗案
	 */
	DKZPA(40, "贷款诈骗案"),
	/**
	 * 故意杀人案
	 */
	GYSRA(41, "故意杀人案"),
	/**
	 * 非法获取国家秘密案
	 */
	FFHQGJMMA(42, "非法获取国家秘密案"),
	/**
	 * 破坏电力设备案
	 */
	PHDLSBA(43, "破坏电力设备案"),
	/**
	 * 运送他人偷越国（边）境案
	 */
	YSTRTYGJA(44, "运送他人偷越国（边）境案"),
	/**
	 * 拐卖妇女儿童案
	 */
	GMFNETA(45, "拐卖妇女儿童案"),
	/**
	 * 非法持有枪支弹药案
	 */
	FFCYQZDYA(46, "非法持有枪支弹药案"),
	/**
	 * 组织他人偷越国（边）境案
	 */
	ZZTRTYGJA(47, "组织他人偷越国（边）境案"),
	/**
	 * 容留他人吸毒案
	 */
	RLTRXDA(48, "容留他人吸毒案"),
	/**
	 * 拦路抢劫案
	 */
	LLQJA(49, "拦路抢劫案"),
	/**
	 * 猥亵儿童案
	 */
	WXETA(50, "猥亵儿童案"),
	/**
	 * 金融诈骗案
	 */
	JRZPA(51, "金融诈骗案"),
	/**
	 * 文娱场所从业人员
	 */
	// WYCSCYRY(52,"文娱场所从业人员"),
	/**
	 * 参加黑社会性质组织案
	 */
	CJHSHXZZZA(53, "参加黑社会性质组织案"),
	/**
	 * 放火案
	 */
	FHA(54, "放火案"),
	/**
	 * 组织、领导黑社会性质组织案
	 */
	ZZLDHSHXZA(55, "组织、领导黑社会性质组织案"),
	/**
	 * 贩卖毒品案
	 */
	FMDPA(56, "贩卖毒品案"),
	/**
	 * 破坏共有电信设备案
	 */
	PHGYDXSBA(57, "破坏共有电信设备案"),
	/**
	 * 保险诈骗案
	 */
	BXZPA(58, "保险诈骗案"),
	/**
	 * 非法制造枪支弹药案
	 */
	FFZZQZDYA(59, "非法制造枪支弹药案"),
	/**
	 * 盗掘古墓葬案
	 */
	DJGMZA(60, "盗掘古墓葬案"),
	/**
	 * 非法买卖枪支弹药案
	 */
	FFMMQZDYA(61, "非法买卖枪支弹药案"),
	/**
	 * 聚众扰乱社会秩序案
	 */
	JZRLSHZXA(62, "聚众扰乱社会秩序案"),
	/**
	 * 扒窃案
	 */
	PQA(63, "扒窃案"),
	/**
	 * 集资诈骗案
	 */
	JZZPA(64, "集资诈骗案"),
	/**
	 * 非法买卖爆炸物案
	 */
	FFMMBZWA(65, "非法买卖爆炸物案"),
	/**
	 * 运输毒品案
	 */
	YSDPA(66, "运输毒品案"),
	/**
	 * 非法持有毒品案
	 */
	FFCYDPA(67, "非法持有毒品案"),
	/**
	 * 偷越国（边）境案
	 */
	TYGJA(68, "偷越国（边）境案"),
	/**
	 * 盗窃路财案
	 */
	DQLCA(69, "盗窃路财案"),
	/**
	 * 盗窃汽车案
	 */
	DQQCA(70, "盗窃汽车案"),
	/**
	 * 非法制造爆炸物案
	 */
	FFZZBZWA(71, "非法制造爆炸物案"),
	/**
	 * 窝藏，转移，隐瞒，毒品毒赃案
	 */
	WCZYYMDPDZA(72, "窝藏，转移，隐瞒，毒品毒赃案"),
	/**
	 * 使用假币违法犯罪案件
	 */
	SYJBWFFZAJ(73, "使用假币违法犯罪案件"),
	/**
	 * 非法私藏枪支弹药案
	 */
	FFSCQZDYA(74, "非法私藏枪支弹药案"),
	/**
	 * 侵犯人身权利案
	 */
	QFRSQLA(75, "侵犯人身权利案"),
	/**
	 * 金融凭证诈骗案
	 */
	JRPZZPA(76, "金融凭证诈骗案"),
	/**
	 * 破坏交通设施案
	 */
	PHJTSSA(77, "破坏交通设施案"),
	/**
	 * 入室盗窃保险柜案件
	 */
	RSDQBXGAJ(78, "入室盗窃保险柜案件"),
	/**
	 * 擅自发行股票、公司企业债券案
	 */
	SZFXGPGSQYZQA(79, "擅自发行股票、公司企业债券案"),
	/**
	 * 聚众斗殴案
	 */
	// JZDOA(80,"聚众斗殴案"),
	/**
	 * 入户抢劫案
	 */
	RHQJA(81, "入户抢劫案"),
	/**
	 * 聚众扰乱公共场所秩序案
	 */
	JZRLGGCSZXA(82, "聚众扰乱公共场所秩序案"),
	/**
	 * 聚众扰乱交通秩序案
	 */
	JZRLJTZXA(83, "聚众扰乱交通秩序案"),
	/**
	 * 盗窃运输物资案
	 */
	DQYSWZA(84, "盗窃运输物资案"),
	/**
	 * 非法买卖制毒物品案
	 */
	FFMMZDWPA(85, "非法买卖制毒物品案"),
	/**
	 * 非法运输爆炸物案
	 */
	FFYSBZWA(86, "非法运输爆炸物案"),
	/**
	 * 奸淫幼女案
	 */
	JYYNA(87, "奸淫幼女案"),
	/**
	 * 破坏易燃易爆设备案
	 */
	PHYRYBSBA(88, "破坏易燃易爆设备案"),
	/**
	 * 破坏交通工具案
	 */
	PHJTGJA(89, "破坏交通工具案"),
	/**
	 * 出售假币案
	 */
	CSJBA(90, "出售假币案"),
	/**
	 * 非法储存爆炸物案
	 */
	FFCCBZWA(91, "非法储存爆炸物案"),
	/**
	 * 传授犯罪方法案
	 */
	CSFZFFA(92, "传授犯罪方法案"),
	/**
	 * 盗掘古文化遗址案
	 */
	DJGWHYZA(93, "盗掘古文化遗址案"),
	/**
	 * 抢劫出租汽车案
	 */
	QJCZQCA(94, "抢劫出租汽车案"),
	/**
	 * 破坏广播电视设备案
	 */
	PHGBDSSBA(95, "破坏广播电视设备案"),;

	private int id;
	private String description;

	SupervisoryCategory(int id, String description) {
		this.id = id;
		this.description = description;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private static final Map<Integer, SupervisoryCategory> DIRC = new HashMap<Integer, SupervisoryCategory>();

	static {
		for (SupervisoryCategory e : SupervisoryCategory.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static SupervisoryCategory byId(int id) {
		return DIRC.get(id);
	}
}

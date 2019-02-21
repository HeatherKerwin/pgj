package com.utiexian.utils.cibfintech;

/**
 * 兴业数金配置
 * @author WKX
 */
public class CibConfig {
	
	private static final String huanjing = "TEST";//环境

	public static String APPID = null;//执剑人、倚天剑通用
	
	public static String DOMAIN = null;//执剑人[请求路径]
	public static String ECONTRACT_DOMAIN = null;//倚天剑[请求路径]

	/**
	 * 数金公钥（测试）
	 */
	public static String UFM_PUBLIC_KEY = null;
	
	/**
	 * 平台私钥（测试）执剑人、倚天剑通用
	 */
	public static String EXCH_PRIVATE_KEY = null;
	
	/**
	 * 平台公钥（测试）
	 */
	public static String EXCH_PUBLIC_KEY = null;
	
	static{
    	if("TEST".equals(huanjing)){
    		APPID = "a2be20717a1c4936b9342ca37f5ed795";
    		DOMAIN = "http://ufm-test.cibfintech.com";//测试环境
    		UFM_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCNL+iNuvg1CBcW9BI3Fo3q4UVBO5XURUy8tIfCkextFi5wbvgGZBTTU0xLPQnp/alq5gbEwfX6lwS+zIg/WnUF2hsYRAaCJYRvlGYpDC0lHZOf4gwCZPtRnwkOQvF9NcJPrOYWp6t85VlIUWn/IZUYEZ31DBFPqln9eAcAuO02BwIDAQAB";
    		EXCH_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKSo5fktzm9DXT34ZaiyKRueN5H2Tj+qPivavqY/x4LX7/zPR1X877Ebmvi19nNydwdsQWwR8TSJfi/MDNwzOKDHdjAkkCbK1yAOEvnBLuCax/OhlT6NF9a9o5h7SHkZ4nsliqbpLek3XYWPPvIX5PbJpVEkiCggf4qD6if7+vpTAgMBAAECgYAeG1/JWu3G/Es9PIDiAolvqlNA1gdirq8ld56qaTkCnJcd44yIlXICMSj51tOUMla/PbUMnI886vLurGGhlaABwS7Ji9J66rKT2XZGC/lpl6bORSRlJ1ByxSn5gOgrP5+PCKhINQAR1BVyOv8zFjKk6nfOvlBkh7JEvLb6Qwi2cQJBAPvloDVFSd/NkqPJ1cKA/0HAq45laiCRMkT/LOFrBsFtcOKl19AmJHhmmx5aE3AVupbavh++hAI7NpjEwmLMwlsCQQCnV4GAVE+t+Db/otBqRyFv6/WMaTo6SYpv8+ZmyqosTYDeDQSmeQrATfc6c0yQDxapAINK4PO9C6QTOvQHezlpAkBej1LW7I3Q4AD+T1RZUceAzW0ZZWSzmQ3/7LLSZDUDA6xuyMb9MnRaZlowyKunVeDXpIHetMwlckkKjEJUiH0lAkAdAlVnyrXZYbsfC7l3gwcv4Ma7ZY57hj4idDSPwzhG39SkKbuRpFAR0DI4hr5SBtuVxon8FA0My5TQ5JpjJaBpAkEAtIrlf6bXC4rRCsaPij1UXhWMVm9wYZDs03Hhs3Pge7pXGcJgtPJsFj8W8b5fO38ivW3ofNKPf6T+POEJSjXZZw==";
    		EXCH_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCkqOX5Lc5vQ109+GWosikbnjeR9k4/qj4r2r6mP8eC1+/8z0dV/O+xG5r4tfZzcncHbEFsEfE0iX4vzAzcMzigx3YwJJAmytcgDhL5wS7gmsfzoZU+jRfWvaOYe0h5GeJ7JYqm6S3pN12Fjz7yF+T2yaVRJIgoIH+Kg+on+/r6UwIDAQAB";
    		
    		ECONTRACT_DOMAIN = "https://43.254.150.41";//倚天剑请求路径
    	}else if("PROD".equals(huanjing)){
    		APPID = "0db969e9f2f04d639f8066e5bf38a37c";
    		DOMAIN = "https://ufm.cibfintech.com";//正式环境
    		UFM_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC6D2LkTNy2VXOejyHE9tsEu+4uDqROeB846DkeNfQrUSeaM/WssGlU8eTpWRCwRMmKw3iWGsbeJEVpK235uihbM33M6Tae6xlGyqrzjuTxSohBw5KDn0TR+SQ+FAlIIWnDIrt0RuEGPlNqq4IfiejFaMcClXZOOoYyZG5T4BYoAQIDAQAB";
    		EXCH_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKPyDFtecPVOQvdWbY+9Qua3rTQXsFDn8QsL/o8A2Bkh+odIDlewnI1I7UA/fRx/4b1tLoSzYPy4H65GVvqG3zwhbY2KkbfnxlpQwCPk+XmY9QKwCrnnVKGmZnb6NYVIV8XUcfltnAh6M503VqVcAaTNP3MZrUyoBJK7pWRR1vA5AgMBAAECgYEAngbbOFshIKLuU8EgXHP+/6s+rb7DukjIBHDsT2Vt51PkS+/9uzLGxDzuS4anDwiJTHDobQsGyrKj/vVYM6Y7mAU70V2Vt+CGt2YsORDmcRpXw6BMvLZsvi8EObygC3u/19YRwenoK4CJxMfxJFKZK2mlF4pZdbe5o2DIxvY7LF0CQQD3vOTvuMnNYnkOKUpCax0Q5Jf+yZLTO5FVLOHmKTp8pPCFLqA59Gz0KrfMqinkMDKaocxYR6IyfevrmTJGgrpHAkEAqWnDCqzyTv9JAzGPqQiytWhy/dyBQQBxP/SiXOzEc82AFP5ysoaYIPbo73hexwazKPDDYWbVK+5oO32hPIvBfwJANa2ADVEEqLPFdwQLSwjaa2acaGBUp+AoRURMTgpw2cEDPFg171i+osj+uiHEoeSvkkGsfRRIWJhgHdQ7yHta0QJBAJAlDvD8akdQ6vyupL98SSIVNxJNFiUKjVGPgr5yLk0h4wL55PmFj9csftGSp4HMb5A8GuG7L1HrzgAgSppLh10CQBzd6B4et5jSsYlVERRX7VX1/aAb1l7HDLYgN0hVeR0gt8sdXBOPk3ZSXOHgZP/ViPjianIJjBqRrrgnv0wGp1w=";
    		EXCH_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCj8gxbXnD1TkL3Vm2PvULmt600F7BQ5/ELC/6PANgZIfqHSA5XsJyNSO1AP30cf+G9bS6Es2D8uB+uRlb6ht88IW2NipG358ZaUMAj5Pl5mPUCsAq551ShpmZ2+jWFSFfF1HH5bZwIejOdN1alXAGkzT9zGa1MqASSu6VkUdbwOQIDAQAB";
    	
    		ECONTRACT_DOMAIN = "https://esign.cibfintech.com";//倚天剑请求路径
    	}
    }
}
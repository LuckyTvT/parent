<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Random"%>
<%@ page import="java.io.OutputStream"%>
<%@ page import="java.awt.Color"%>
<%@ page import="java.awt.Font"%>
<%@ page import="java.awt.Graphics"%>
<%@ page import="java.awt.image.BufferedImage"%>
<%@ page import="javax.imageio.ImageIO"%>
<%
	// 使用java图形界面技术绘制一张图片
	int charNum = 4;
	int width = 20 * 4;
	int height = 32;

	// 1. 创建一张内存图片
	BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	// 2.获得绘图对象
	Graphics graphics = bufferedImage.getGraphics();

	// 3、绘制背景颜色
	graphics.setColor(new Color(0xDCDCDC));
	graphics.fillRect(0, 0, width, height);

	// 4、绘制图片边框
	graphics.setColor(Color.GRAY);
	graphics.drawRect(0, 0, width - 1, height - 1);

	// 5、设置字体颜色和属性
	graphics.setColor(Color.RED);
	graphics.setFont(new Font("宋体", Font.BOLD, 22));

	// 随机输出4个字符
	String s = "ABCDEFGHIGKLMNPQRSTUVWXYZ123456789";
	Random random = new Random();

	// session中要用到
	String msg = "";

	int x = 5;
	for (int i = 0; i < charNum; i++) {
		int index = random.nextInt(32);
		String content = String.valueOf(s.charAt(index));

		msg += content;
		graphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
		graphics.drawString(content, x, 22);
		x += 20;
	}


	// 6、绘制干扰线
	graphics.setColor(Color.GRAY);
	for (int i = 0; i < 5; i++) {
		int x1 = random.nextInt(width);
		int x2 = random.nextInt(width);

		int y1 = random.nextInt(height);
		int y2 = random.nextInt(height);
		graphics.drawLine(x1, y1, x2, y2);
	}

//	session = request.getSession();
	session.setAttribute("validateCode",msg);

	out.clear();
	out = pageContext.pushBody();

	// 释放资源
	graphics.dispose();

	// 图片输出 ImageIO
	ImageIO.write(bufferedImage, "jpg", response.getOutputStream());


%>
// 代码生成时间: 2025-09-17 18:46:53
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 图片尺寸批量调整器
 * 该类用于批量调整文件夹中图片的尺寸。
 */
public class ImageResizer {

    /**
     * 调整图片尺寸的方法。
     *
     * @param sourceFolder 源文件夹路径
     * @param targetFolder 目标文件夹路径
     * @param targetWidth  目标宽度
     * @param targetHeight 目标高度
     * @throws IOException 如果读取或写入文件时发生错误
     */
    public void resizeImages(String sourceFolder, String targetFolder, int targetWidth, int targetHeight) throws IOException {
        File folder = new File(sourceFolder);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && isImageFile(file)) {
                    BufferedImage originalImage = ImageIO.read(file);
                    BufferedImage resizedImage = resizeImage(originalImage, targetWidth, targetHeight);
                    File targetFile = new File(targetFolder + File.separator + file.getName());
                    ImageIO.write(resizedImage, getExtension(file), targetFile);
                }
            }
        } else {
            throw new IOException("源文件夹不存在或不是一个文件夹。");
        }
    }

    /**
     * 检查文件是否为图片文件。
     *
     * @param file 文件
     * @return 如果是图片文件返回true，否则返回false。
     */
    private boolean isImageFile(File file) {
        String fileName = file.getName();
        return fileName.toLowerCase().endsWith(".png") || fileName.toLowerCase().endsWith(".jpg") ||
                fileName.toLowerCase().endsWith(".jpeg") || fileName.toLowerCase().endsWith(".gif");
    }

    /**
     * 获取文件扩展名。
     *
     * @param file 文件
     * @return 文件扩展名
     */
    private String getExtension(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 调整图片尺寸。
     *
     * @param image     原始图片
     * @param newWidth  新宽度
     * @param newHeight 新高度
     * @return 调整后的图片
     */
    private BufferedImage resizeImage(BufferedImage image, int newWidth, int newHeight) {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        double scaleWidth = (double) newWidth / imageWidth;
        double scaleHeight = (double) newHeight / imageHeight;
        double scale = Math.min(scaleWidth, scaleHeight);
        int scaledWidth = (int) (imageWidth * scale);
        int scaledHeight = (int) (imageHeight * scale);

        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, image.getType());
        Graphics2D graphics2D = resizedImage.createGraphics();

        graphics2D.drawImage(image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH),
                (newWidth - scaledWidth) / 2, (newHeight - scaledHeight) / 2, null);
        graphics2D.dispose();

        return resizedImage;
    }
}

package rafa.code;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

// Менеджер вертикального расположения компонентов
public class VerticalLayout implements LayoutManager {
    private Dimension size = new Dimension();
    private int widthC = 0;

    // Следующие два метода не используются
    @Override
    public void addLayoutComponent(String name, Component comp) {}
    
    @Override
    public void removeLayoutComponent(Component comp) {}
    
    
    public VerticalLayout(){

    }
    // Метод определения мndexинимального размера для контейнера
    @Override
    public Dimension minimumLayoutSize(Container c) {
        return calculateBestSize(c);
    }
    // Метод определения предпочтительного размера для контейнера
    @Override
    public Dimension preferredLayoutSize(Container c) {
        return calculateBestSize(c);
    }
    // Метод расположения компонентов в контейнере
    @Override
    public void layoutContainer(Container container) {

        // Список компонентов
        Component[] list = container.getComponents();
        int currentY = 1;
        widthC = container.getWidth();
        for (Component list1 : list) {
            // Определение предпочтительного размера компонента
            Dimension pref = list1.getPreferredSize();
            if (null == list1.getName()) {
                list1.setBounds(0, currentY, pref.width, pref.height);
            } else switch (list1.getName()) {
                case "0":
                    list1.setBounds((container.getWidth() / 2 - (pref.width / 2)), currentY, pref.width, pref.height);
                    // list[i].setSize(new Dimension(container.getWidth()/2-(pref.width/2), pref.height));
                    break;
                case "00":
                    list1.setBounds((container.getWidth() / 2 - (pref.width / 2)), (container.getHeight() / 2 - (pref.height / 2)), pref.width, pref.height);
                    break;
                case "1":
                    list1.setBounds(10, currentY, pref.width, pref.height);
                    break;
                case "2":
                    list1.setBounds(container.getWidth() - pref.width - 5, currentY, pref.width, pref.height);
                    break;
                default:
                    list1.setBounds(0, currentY, pref.width, pref.height);
                    break;
            }
            // Размещение компонента на экране
            //list[i].setBounds(x, currentY, pref.width, pref.height);
            // Учитываем промежуток в 5 пикселов
            // currentY += 1;
            // Смещаем вертикальную позицию компонента
            currentY += pref.height;
        }
    }
    // Метод вычисления оптимального размера контейнера
    private Dimension calculateBestSize(Container c) {
        // Вычисление длины контейнера
        Component[] list = c.getComponents();
        int maxWidth = widthC/2 ;
        int height = 0;
        for (Component list1 : list) {

            int width = list1.getWidth();
            // Поиск компонента с максимальной длиной
            if (width < maxWidth)
                width = maxWidth;
        }
        // Размер контейнера в длину с учетом левого отступа
        size.width = maxWidth + 5;
        // Вычисление высоты контейнера
        for (Component list1 : list) {
            height += 5;
            height += list1.getHeight();
        }
        size.height = height;
        return size;
    }
}
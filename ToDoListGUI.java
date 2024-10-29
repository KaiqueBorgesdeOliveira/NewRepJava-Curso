import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListGUI {
    private JFrame frame;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskInputField;

    public ToDoListGUI() {
        frame = new JFrame("Lista de Tarefas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setMinimumSize(new Dimension(400, 400));

        // Modelo da lista de tarefas
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.setFont(new Font("Arial", Font.PLAIN, 16));
        taskList.setFixedCellHeight(30);

        // Painel personalizado para exibir a marca d'água ASCII
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Configurações da fonte e cor da marca d'água
                g.setFont(new Font("Monospaced", Font.PLAIN, 10));
                g.setColor(new Color(200, 200, 200, 50)); // Cor clara e transparente

                // Desenho ASCII no estilo marca d'água
                String[] asciiArt = {
                        "         .::::.        ",
                        "       .::::::::.      ",
                        "     ::::::::::::::    ",
                        "  ..:::::::::::::::'   ",
                        " ':::::::::::::::'     ",
                        " ::::::::::::::'       ",
                        " '::::::::::'          ",
                        "   '::::::'            "
                };

                // Posiciona o desenho ASCII no centro do painel
                int y = 100;
                for (String line : asciiArt) {
                    g.drawString(line, 120, y += 12); // Ajuste de posição X e Y de cada linha
                }
            }
        };
        backgroundPanel.setLayout(new BorderLayout(10, 10));

        // Campo de entrada de tarefa e botões
        taskInputField = new JTextField();
        taskInputField.setFont(new Font("Arial", Font.PLAIN, 16));
        taskInputField.setBorder(BorderFactory.createTitledBorder("Nova Tarefa"));

        JButton addButton = new JButton("Adicionar");
        JButton completeButton = new JButton("Concluir");
        JButton deleteButton = new JButton("Remover");

        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        completeButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Adiciona cores para os botões
        addButton.setBackground(new Color(102, 205, 170));
        completeButton.setBackground(new Color(255, 215, 0));
        deleteButton.setBackground(new Color(255, 99, 71));
        addButton.setForeground(Color.WHITE);
        completeButton.setForeground(Color.BLACK);
        deleteButton.setForeground(Color.WHITE);

        // Painel para entrada e botão de adicionar
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.add(taskInputField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        // Painel para botões de ações
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        actionPanel.add(completeButton);
        actionPanel.add(deleteButton);

        // Adiciona a lista de tarefas no centro do painel de fundo
        backgroundPanel.add(new JScrollPane(taskList), BorderLayout.CENTER);
        backgroundPanel.add(inputPanel, BorderLayout.NORTH);
        backgroundPanel.add(actionPanel, BorderLayout.SOUTH);

        frame.add(backgroundPanel);
        frame.setVisible(true);

        // Ações dos botões
        addButton.addActionListener(e -> {
            String task = taskInputField.getText().trim();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                taskInputField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, digite uma tarefa!");
            }
        });

        completeButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                String task = taskListModel.get(selectedIndex);
                taskListModel.set(selectedIndex, task + " (Concluída)");
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione uma tarefa para marcar como concluída.");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                taskListModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione uma tarefa para remover.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoListGUI::new);
    }
}

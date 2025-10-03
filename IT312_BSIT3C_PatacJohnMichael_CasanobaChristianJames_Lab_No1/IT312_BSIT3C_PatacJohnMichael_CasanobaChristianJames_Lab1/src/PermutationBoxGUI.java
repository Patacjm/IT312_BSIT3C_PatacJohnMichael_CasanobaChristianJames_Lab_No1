import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class PermutationBoxGUI {

    // SPBOX FUNCTION
    private static String runSPBOX(String input) {
        List<Character> chars = new ArrayList<>();
        for (char c : input.toCharArray()) chars.add(c);
        Collections.shuffle(chars);

        StringBuilder result = new StringBuilder();
        for (char c : chars) result.append(c);

        return result.toString();
    }

    // CPBOX FUNCTION
    private static String runCPBOX(String input) {
        List<Character> chars = new ArrayList<>();
        for (char c : input.toCharArray()) chars.add(c);
        Collections.shuffle(chars);

        int half = chars.size() / 2;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < half; i++) {
            result.append(chars.get(i));
        }

        return result.toString();
    }

    // EPBOX FUNCTION
    private static String runEPBOX(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            result.append(c).append(c);
        }
        return result.toString();
    }

    // EXPLANATION PROVIDER
    private static String getExplanation(String boxType, String input, String output) {
        switch (boxType) {
            case "SP-BOX":
                return "SPBOX Explaination:\nThe plain text letters were shuffled randomly.";
            case "CP-BOX":
                return "CPBOX Explaination:\nA random subset of half of the letters was taken.";
            case "EP-BOX":
                return "EPBOX Explaination:\nEach letter was duplicated to form the cipher.";
            default:
                return "[No explanation available]";
        }
    }

    // MAIN MENU
    private static void createMainMenu() {
        JFrame frame = new JFrame("PERMUTATIONS BOX");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel title = new JLabel("PERMUTATIONS BOX", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(title);

        JButton spButton = new JButton("Straight P-BOX");
        spButton.setBackground(Color.RED);
        spButton.setForeground(Color.BLACK);
        spButton.addActionListener(e -> openPermutationWindow("SP-BOX"));
        frame.add(spButton);

        JButton cpButton = new JButton("Compression P-BOX");
        cpButton.setBackground(Color.GREEN);
        cpButton.setForeground(Color.BLACK);
        cpButton.addActionListener(e -> openPermutationWindow("CP-BOX"));
        frame.add(cpButton);

        JButton epButton = new JButton("Expansion P-BOX");
        epButton.setBackground(Color.BLUE);
        epButton.setForeground(Color.BLACK);
        epButton.addActionListener(e -> openPermutationWindow("EP-BOX"));
        frame.add(epButton);

        frame.setVisible(true);
    }

    // PERMUTATION WINDOW
    private static void openPermutationWindow(String selected) {
        JFrame frame = new JFrame(selected + " Window");
        frame.setSize(450, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Enter Plain Text:"));
        JTextField inputField = new JTextField(15);
        topPanel.add(inputField);

        JButton processBtn = new JButton("Encrypt");
        topPanel.add(processBtn);

        frame.add(topPanel, BorderLayout.NORTH);

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        processBtn.addActionListener(e -> {
            String input = inputField.getText().trim();
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                        "Please enter letters.","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String output;
            String explanation;

            switch (selected) {
                case "SP-BOX":
                    output = runSPBOX(input);
                    explanation = getExplanation("SP-BOX", input, output);
                    break;
                case "CP-BOX":
                    output = runCPBOX(input);
                    explanation = getExplanation("CP-BOX", input, output);
                    break;
                case "EP-BOX":
                    output = runEPBOX(input);
                    explanation = getExplanation("EP-BOX", input, output);
                    break;
                default:
                    output = "[Invalid Box]";
                    explanation = "";
            }

            outputArea.setText("");
            outputArea.append("Cipher Result: " + output + "\n\n");
            outputArea.append(explanation);
        });

        frame.setVisible(true);
    }

    // MAIN METHOD
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PermutationBoxGUI::createMainMenu);
    }
}

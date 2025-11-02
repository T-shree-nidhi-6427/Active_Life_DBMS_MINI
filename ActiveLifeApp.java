import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ActiveLifeApp {

    // User data storage
    static class UserData {
        String name = "";
        String age = "";
        String healthIssues = "";
        String seriousConditions = "";
        String currentLanguage = "English";
    }

    static UserData userData = new UserData();

    // Language translations
    static Map<String, Map<String, String>> translations = new HashMap<>();

    static {
        // English
        Map<String, String> english = new HashMap<>();
        english.put("title", "Active Life");
        english.put("name", "Name:");
        english.put("age", "Age:");
        english.put("health", "Current Health Issues:");
        english.put("serious", "Serious Conditions:");
        english.put("submit", "Submit");
        english.put("welcome", "Welcome to Active Life");
        english.put("bmi", "BMI Calculator");
        english.put("targets", "Targets");
        english.put("diet", "Diet Plans");
        english.put("workout", "Workout Plans");
        english.put("profile", "Profile");
        english.put("settings", "Settings");
        translations.put("English", english);

    }

    static String translate(String key) {
        return translations.get(userData.currentLanguage).getOrDefault(key, key);
    }

    public static void main(String[] args) {
        showLoginPage();
    }

    static void showLoginPage() {
        JFrame frame = new JFrame("ActiveLife");
        frame.setSize(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(Color.white);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("ActiveLife", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        c.gridwidth = 2;
        c.gridx = 0; c.gridy = 0;
        frame.add(titleLabel, c);

        c.gridwidth = 1;
        JLabel nameLabel = new JLabel("Name:");
        c.gridx = 0; c.gridy = 1;
        frame.add(nameLabel, c);

        JTextField nameField = new JTextField(20);
        c.gridx = 1; c.gridy = 1;
        frame.add(nameField, c);

        JLabel ageLabel = new JLabel("Age:");
        c.gridx = 0; c.gridy = 2;
        frame.add(ageLabel, c);

        JTextField ageField = new JTextField(20);
        c.gridx = 1; c.gridy = 2;
        frame.add(ageField, c);

        JLabel healthLabel = new JLabel("Current Health Issues:");
        c.gridx = 0; c.gridy = 3;
        frame.add(healthLabel, c);

        JTextField healthField = new JTextField("e.g., Low BP, Iron Deficiency", 20);
        c.gridx = 1; c.gridy = 3;
        frame.add(healthField, c);

        JLabel conditionLabel = new JLabel("Serious Conditions:");
        c.gridx = 0; c.gridy = 4;
        frame.add(conditionLabel, c);

        JTextArea conditionArea = new JTextArea("e.g., Asthma 15 yrs, Surgery at 25", 4, 20);
        conditionArea.setLineWrap(true);
        conditionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(conditionArea);
        c.gridx = 1; c.gridy = 4;
        frame.add(scrollPane, c);

        JButton submitButton = new JButton("Submit");
        c.gridwidth = 2;
        c.gridx = 0; c.gridy = 5;
        frame.add(submitButton, c);

        submitButton.addActionListener(e -> {
            userData.name = nameField.getText();
            userData.age = ageField.getText();
            userData.healthIssues = healthField.getText();
            userData.seriousConditions = conditionArea.getText();

            frame.dispose();
            showHomePage();
        });

        frame.setVisible(true);
    }

    static void showHomePage() {
        JFrame frame = new JFrame(translate("title") + " - Home");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topBar = new JPanel();
        topBar.setBackground(new Color(56, 177, 71));
        topBar.setPreferredSize(new Dimension(frame.getWidth(), 60));
        topBar.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 15));
        JLabel titleLabel = new JLabel(translate("title"));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        topBar.add(titleLabel);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(224, 252, 232));
        leftPanel.setPreferredSize(new Dimension(220, frame.getHeight()));
        leftPanel.setLayout(new GridLayout(6, 1, 0, 30));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(45, 25, 45, 25));

        JButton bmiBtn = new JButton(translate("bmi"));
        JButton targetsBtn = new JButton(translate("targets"));
        JButton dietBtn = new JButton(translate("diet"));
        JButton workoutBtn = new JButton(translate("workout"));
        JButton profileBtn = new JButton(translate("profile"));
        JButton settingsBtn = new JButton(translate("settings"));

        Font leftFont = new Font("Arial", Font.BOLD, 16);
        bmiBtn.setFont(leftFont); targetsBtn.setFont(leftFont);
        dietBtn.setFont(leftFont); workoutBtn.setFont(leftFont);
        profileBtn.setFont(leftFont); settingsBtn.setFont(leftFont);

        leftPanel.add(bmiBtn);
        leftPanel.add(targetsBtn);
        leftPanel.add(dietBtn);
        leftPanel.add(workoutBtn);
        leftPanel.add(profileBtn);
        leftPanel.add(settingsBtn);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);

// Use horizontal BoxLayout for text + image side by side
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));

// 1. Create and style welcome text
        JLabel welcomeLabel = new JLabel(translate("welcome"));
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 32));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30)); // space between text and image

// 2. Load and scale image
        ImageIcon icon = new ImageIcon("src/Download couple exercising together in the house for free.jpg");

        Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH); // 120x120px is neat beside 32-38pt text
        ImageIcon scaledIcon = new ImageIcon(img);
        JLabel imageLabel = new JLabel(scaledIcon);

// Load and scale image for left of welcome text
        ImageIcon leftIcon = new ImageIcon("src/How to meditate.jpg");
        Image leftImg = leftIcon.getImage().getScaledInstance(95, 95, Image.SCALE_SMOOTH); // size: medium and square
        ImageIcon scaledLeftIcon = new ImageIcon(leftImg);
        JLabel leftImageLabel = new JLabel(scaledLeftIcon);

// Add image before welcome text, and your existing image after
        centerPanel.add(Box.createHorizontalGlue());
        centerPanel.add(leftImageLabel);     // <--- Meditation image
        centerPanel.add(Box.createHorizontalStrut(26)); // spacing between image and text
        centerPanel.add(welcomeLabel);
        centerPanel.add(Box.createHorizontalStrut(26)); // spacing between text and right image
        centerPanel.add(imageLabel);         // your existing image (e.g., runner)
        centerPanel.add(Box.createHorizontalGlue());



        // BMI Calculator with protein recommendation
        bmiBtn.addActionListener(e -> {
            JTextField ageFieldBMI = new JTextField(5);
            JTextField heightField = new JTextField(5);
            JTextField weightField = new JTextField(5);

            JPanel bmiPanel = new JPanel(new GridLayout(3, 2, 5, 5));
            bmiPanel.add(new JLabel("Age:"));
            bmiPanel.add(ageFieldBMI);
            bmiPanel.add(new JLabel("Height (cm):"));
            bmiPanel.add(heightField);
            bmiPanel.add(new JLabel("Weight (kg):"));
            bmiPanel.add(weightField);

            int result = JOptionPane.showConfirmDialog(frame, bmiPanel,
                    "Enter your details to calculate BMI", JOptionPane.OK_CANCEL_OPTION);
            Window[] windows = Window.getWindows();
            for (Window w : windows) {
                if (w instanceof JDialog) {
                    w.setSize(680, 300); // Adjust as needed
                    w.setMinimumSize(new Dimension(680, 300));
                }
            }


            if (result == JOptionPane.OK_OPTION) {
                try {
                    int age = Integer.parseInt(ageFieldBMI.getText());
                    double height = Double.parseDouble(heightField.getText());
                    double weight = Double.parseDouble(weightField.getText());
                    double bmi = weight / ((height / 100) * (height / 100));
                    double proteinNeeded = weight * 0.8;

                    String status;
                    if (bmi < 18.5) {
                        status = "Underweight — Consider a diet rich in calories and nutrients to gain healthy weight.";
                    } else if (bmi < 25) {
                        status = "Normal — Keep maintaining your healthy lifestyle!";
                    } else if (bmi < 30) {
                        status = "Overweight — Consider a balanced diet and regular exercise to reduce weight.";
                    } else {
                        status = "Obese — Seek medical advice and follow a dedicated weight-loss plan.";
                    }
                    JOptionPane.showMessageDialog(frame,
                            String.format("Age: %d\nBMI: %.1f\nStatus: %s\n\nRECOMMENDED PROTEIN PER DAY: %.1f grams",
                                    age, bmi, status, proteinNeeded),
                            "Your BMI Result", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Please enter valid numbers for all fields.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Targets
        targetsBtn.addActionListener(e -> {
            JTextField currentWeightField = new JTextField(5);
            JTextField targetWeightField = new JTextField(5);
            String[] goals = {"Lose Weight", "Gain Weight", "Maintain Weight"};
            JComboBox<String> goalBox = new JComboBox<>(goals);

            JPanel targetPanel = new JPanel(new GridLayout(3, 2, 5, 5));
            targetPanel.add(new JLabel("Current Weight (kg):"));
            targetPanel.add(currentWeightField);
            targetPanel.add(new JLabel("Target Weight (kg):"));
            targetPanel.add(targetWeightField);
            targetPanel.add(new JLabel("Goal:"));
            targetPanel.add(goalBox);

            int result = JOptionPane.showConfirmDialog(frame, targetPanel,
                    "Set Your Target", JOptionPane.OK_CANCEL_OPTION);
            Window[] windows = Window.getWindows();
            for (Window w : windows) {
                if (w instanceof JDialog) {
                    w.setSize(650, 280);
                    w.setMinimumSize(new Dimension(650, 280));
                }
            }


            if (result == JOptionPane.OK_OPTION) {
                String message = "Current Weight: " + currentWeightField.getText()
                        + "\nTarget Weight: " + targetWeightField.getText()
                        + "\nGoal: " + goalBox.getSelectedItem();
                JOptionPane.showMessageDialog(frame, message,
                        "Your Target", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Diet Plans - WITH VEG/NON-VEG AND PROTEIN SELECTION
        dietBtn.addActionListener(e -> {
            String[] preferences = {"Veg", "Non-Veg"};
            JComboBox<String> prefBox = new JComboBox<>(preferences);

            String[] proteinOptions = {"10g", "20g", "30g", "40g", "50g", "60g", "70g"};
            JComboBox<String> proteinBox = new JComboBox<>(proteinOptions);
            proteinBox.setSelectedItem("40g"); // Default selection

            JPanel dietPanel = new JPanel(new GridLayout(2, 2, 5, 5));
            dietPanel.add(new JLabel("Diet Preference:"));
            dietPanel.add(prefBox);
            dietPanel.add(new JLabel("Daily Protein Target:"));
            dietPanel.add(proteinBox);

            int result = JOptionPane.showConfirmDialog(frame, dietPanel,
                    "Choose your diet plan", JOptionPane.OK_CANCEL_OPTION);
            Window[] windows = Window.getWindows();
            for (Window w : windows) {
                if (w instanceof JDialog) {
                    w.setSize(525, 230);
                    w.setMinimumSize(new Dimension(525, 230));
                }
            }


            if (result == JOptionPane.OK_OPTION) {
                String preference = (String) prefBox.getSelectedItem();
                String proteinTarget = (String) proteinBox.getSelectedItem();
                String healthCondition = userData.healthIssues.toLowerCase();
                String dietPlan = "";

                if (healthCondition.contains("iron") || healthCondition.contains("anemia") || healthCondition.contains("deficiency")) {
                    dietPlan = getIronDeficiencyDiet(preference, proteinTarget);
                } else if (healthCondition.contains("heart") || healthCondition.contains("cardiac")) {
                    dietPlan = getHeartAttackDiet(preference, proteinTarget);
                } else if (healthCondition.contains("diabetes") || healthCondition.contains("sugar")) {
                    dietPlan = getDiabeticDiet(preference, proteinTarget);
                } else {
                    String[] goals = {"Maintain Weight", "Lose Weight", "Gain Weight"};
                    String goal = (String) JOptionPane.showInputDialog(frame,
                            "Select your goal:", "Diet Plan",
                            JOptionPane.QUESTION_MESSAGE, null, goals, goals[0]);

                    if (goal != null) {
                        dietPlan = getNormalDiet(goal, preference, proteinTarget);
                    }
                }

                if (!dietPlan.isEmpty()) {
                    JTextArea textArea = new JTextArea(dietPlan, 18, 52);
                    textArea.setEditable(false);
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);
                    textArea.setFont(new Font("Arial", Font.BOLD, 21));
                    textArea.setForeground(new Color(32, 70, 44));
                    textArea.setBackground(new Color(237, 255, 245));
                    textArea.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(new Color(56, 177, 71), 4, true),
                            BorderFactory.createEmptyBorder(18, 18, 18, 18)
                    ));
                    JScrollPane scrollPane2 = new JScrollPane(textArea);
                    scrollPane2.setPreferredSize(new Dimension(850, 380));
                    JOptionPane.showMessageDialog(frame, scrollPane2, "Your Diet Plan", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        // Workout Plans - WITH DROPDOWN
        workoutBtn.addActionListener(e -> {
            String[] types = {"Simple Workout", "Cardio", "Strength"};
            JComboBox<String> typeBox = new JComboBox<>(types);

            JPanel workoutPanel = new JPanel(new GridLayout(1, 2, 5, 5));
            workoutPanel.add(new JLabel("Workout Type:"));
            workoutPanel.add(typeBox);

            int result = JOptionPane.showConfirmDialog(frame, workoutPanel,
                    "Choose Workout Plan", JOptionPane.OK_CANCEL_OPTION);
            Window[] windows = Window.getWindows();
            for (Window w : windows) {
                if (w instanceof JDialog) {
                    w.setSize(500, 200);
                    w.setMinimumSize(new Dimension(500, 200));
                }
            }


            if (result == JOptionPane.OK_OPTION) {
                String type = (String) typeBox.getSelectedItem();
                String healthCondition = userData.healthIssues.toLowerCase();
                String workoutPlan = "";

                if (healthCondition.contains("iron") || healthCondition.contains("anemia") || healthCondition.contains("deficiency")) {
                    workoutPlan = getIronDeficiencyWorkout(type);
                } else if (healthCondition.contains("heart") || healthCondition.contains("cardiac")) {
                    workoutPlan = getHeartAttackWorkout(type);
                } else if (healthCondition.contains("diabetes") || healthCondition.contains("sugar")) {
                    workoutPlan = getDiabeticWorkout(type);
                } else {
                    String[] goals = {"Maintain Weight", "Lose Weight", "Gain Weight"};
                    String goal = (String) JOptionPane.showInputDialog(frame,
                            "Select your goal:", "Workout Plan",
                            JOptionPane.QUESTION_MESSAGE, null, goals, goals[0]);

                    if (goal != null) {
                        workoutPlan = getNormalWorkout(goal, type);
                    }
                }

                if (!workoutPlan.isEmpty()) {
                    JTextArea textArea = new JTextArea(workoutPlan, 18, 52);
                    textArea.setEditable(false);
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);
                    textArea.setFont(new Font("Arial", Font.BOLD, 21));
                    textArea.setForeground(new Color(32, 70, 44));
                    textArea.setBackground(new Color(237, 255, 245));
                    textArea.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(new Color(56, 177, 71), 4, true),
                            BorderFactory.createEmptyBorder(18, 18, 18, 18)
                    ));
                    JScrollPane scrollPane2 = new JScrollPane(textArea);
                    scrollPane2.setPreferredSize(new Dimension(850, 380));
                    JOptionPane.showMessageDialog(frame, scrollPane2, "Your Workout Plan", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        // Profile with Edit option
        profileBtn.addActionListener(e -> {
            JTextField nameFieldProfile = new JTextField(userData.name, 15);
            JTextField ageFieldProfile = new JTextField(userData.age, 5);
            JTextField healthFieldProfile = new JTextField(userData.healthIssues, 20);
            JTextArea conditionAreaProfile = new JTextArea(userData.seriousConditions, 3, 20);
            conditionAreaProfile.setLineWrap(true);
            conditionAreaProfile.setWrapStyleWord(true);

            JPanel profilePanel = new JPanel(new GridLayout(4, 2, 5, 5));
            profilePanel.add(new JLabel("Name:"));
            profilePanel.add(nameFieldProfile);
            profilePanel.add(new JLabel("Age:"));
            profilePanel.add(ageFieldProfile);
            profilePanel.add(new JLabel("Health Issues:"));
            profilePanel.add(healthFieldProfile);
            profilePanel.add(new JLabel("Serious Conditions:"));
            profilePanel.add(new JScrollPane(conditionAreaProfile));

            int result = JOptionPane.showConfirmDialog(frame, profilePanel,
                    "Profile (Edit & Save)", JOptionPane.OK_CANCEL_OPTION);
            Window[] windows = Window.getWindows();
            for (Window w : windows) {
                if (w instanceof JDialog) {
                    w.setSize(650, 320);
                    w.setMinimumSize(new Dimension(650, 320));
                }
            }


            if (result == JOptionPane.OK_OPTION) {
                userData.name = nameFieldProfile.getText();
                userData.age = ageFieldProfile.getText();
                userData.healthIssues = healthFieldProfile.getText();
                userData.seriousConditions = conditionAreaProfile.getText();
                JOptionPane.showMessageDialog(frame, "Profile updated successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Settings with Language
        settingsBtn.addActionListener(e -> {
            String[] settingsChoices = {"Language Setting", "Privacy & Security"};
            JComboBox<String> settingsBox = new JComboBox<>(settingsChoices);

            JPanel settingsPanel = new JPanel(new GridLayout(1, 2, 5, 5));
            settingsPanel.add(new JLabel("Choose:"));
            settingsPanel.add(settingsBox);

            int result = JOptionPane.showConfirmDialog(frame, settingsPanel,
                    "Settings", JOptionPane.OK_CANCEL_OPTION);
            Window[] windows = Window.getWindows();
            for (Window w : windows) {
                if (w instanceof JDialog) {
                    w.setSize(500, 200);
                    w.setMinimumSize(new Dimension(500, 200));
                }
            }


            if (result == JOptionPane.OK_OPTION) {
                if ("Language Setting".equals(settingsBox.getSelectedItem())) {
                    String[] languages = {"English", "Tamil", "Telugu", "Malayalam"};
                    JComboBox<String> langBox = new JComboBox<>(languages);
                    langBox.setSelectedItem(userData.currentLanguage);

                    JPanel langPanel = new JPanel(new GridLayout(1, 2, 5, 5));
                    langPanel.add(new JLabel("Select Language:"));
                    langPanel.add(langBox);

                    int langResult = JOptionPane.showConfirmDialog(frame, langPanel,
                            "Language Setting", JOptionPane.OK_CANCEL_OPTION);
                    if (langResult == JOptionPane.OK_OPTION) {
                        userData.currentLanguage = (String) langBox.getSelectedItem();
                        JOptionPane.showMessageDialog(frame,
                                "Language changed! Please restart to see changes.",
                                "Language Setting", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        showHomePage();
                    }
                } else {
                    JPanel privPanel = new JPanel(new GridLayout(2, 1, 5, 5));
                    JCheckBox privacyBox = new JCheckBox("Enable Privacy");
                    JCheckBox securityBox = new JCheckBox("Enable Security");
                    privPanel.add(privacyBox);
                    privPanel.add(securityBox);
                    int privResult = JOptionPane.showConfirmDialog(frame, privPanel,
                            "Privacy & Security", JOptionPane.OK_CANCEL_OPTION);
                    if (privResult == JOptionPane.OK_OPTION) {
                        JOptionPane.showMessageDialog(frame,
                                "Privacy: " + (privacyBox.isSelected() ? "On" : "Off") +
                                        "\nSecurity: " + (securityBox.isSelected() ? "On" : "Off"),
                                "Privacy & Security", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(topBar, BorderLayout.NORTH);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // Diet plan methods with preference AND protein target
    static String getIronDeficiencyDiet(String pref, String proteinTarget) {
        String header = "IRON DEFICIENCY DIET PLAN (" + pref.toUpperCase() + ")\n";
        header += "Target Protein: " + proteinTarget + " per day\n\n";

        if (pref.equals("Veg")) {
            return header +
                    "Breakfast: Fortified oats with milk - 8g protein\n" +
                    "          Almonds + walnuts - 4g protein\n" +
                    "          Orange juice (vitamin C)\n\n" +
                    "Mid-Morning: Banana smoothie (soy milk + flax) - 6g protein\n\n" +
                    "Lunch: Spinach lentil curry (dal palak) - 10g protein\n" +
                    "       Brown rice/quinoa - 4g protein\n" +
                    "       Cabbage salad with lemon\n\n" +
                    "Evening: Roasted chickpeas - 5g protein\n\n" +
                    "Dinner: Tofu stir-fry with broccoli - 7g protein\n" +
                    "        1 small roti\n\n" +
                    "Optional (if more protein needed): Warm milk - 3g protein\n\n" +
                    "Note: Adjust portions to match your " + proteinTarget + " target.";
        } else {
            return header +
                    "Breakfast: 2 boiled eggs + toast - 13g protein\n" +
                    "          Orange juice (vitamin C)\n\n" +
                    "Mid-Morning: Greek yogurt with chia seeds - 8g protein\n\n" +
                    "Lunch: Grilled chicken breast (100g) - 20g protein\n" +
                    "       Spinach curry + brown rice\n\n" +
                    "Evening: Dates + almonds\n\n" +
                    "Dinner: Fish curry (salmon/sardine) - 10g protein\n" +
                    "        Vegetable stir-fry\n\n" +
                    "Note: Adjust portions to match your " + proteinTarget + " target.";
        }
    }

    static String getHeartAttackDiet(String pref, String proteinTarget) {
        String header = "HEART ATTACK RECOVERY DIET (" + pref.toUpperCase() + ")\n";
        header += "Target Protein: " + proteinTarget + " per day\n\n";

        if (pref.equals("Veg")) {
            return header +
                    "Morning: Warm water + lemon + flax seeds\n" +
                    "         Oats porridge with skim milk & nuts\n" +
                    "         1 apple\n\n" +
                    "Mid-Morning: Green tea + 2 crackers\n\n" +
                    "Lunch: Brown rice/2 phulkas + moong dal\n" +
                    "       Stir-fried broccoli, carrots, peas\n" +
                    "       Fresh salad with lemon\n\n" +
                    "Evening: Roasted chickpeas/unsalted nuts\n\n" +
                    "Dinner: Vegetable soup + grilled paneer (100g)\n" +
                    "        1 roti + steamed spinach\n\n" +
                    "AVOID: Fried foods, salt, butter, ghee\n\n" +
                    "Note: Adjust portions to match your " + proteinTarget + " target.";
        } else {
            return header +
                    "Morning: Lemon water + 2 egg whites + whole grain bread\n\n" +
                    "Mid-Morning: Green tea + 5 almonds\n\n" +
                    "Lunch: Grilled chicken (100-120g) or fish (salmon)\n" +
                    "       Brown rice + steamed broccoli & carrots\n\n" +
                    "Evening: Low-fat yogurt + chia seeds\n\n" +
                    "Dinner: Chicken vegetable soup/grilled fish\n" +
                    "        Vegetable stir-fry\n\n" +
                    "AVOID: Fried foods, salt, butter, red meat\n\n" +
                    "Note: Adjust portions to match your " + proteinTarget + " target.";
        }
    }

    static String getDiabeticDiet(String pref, String proteinTarget) {
        String header = "TYPE 2 DIABETES DIET (" + pref.toUpperCase() + ")\n";
        header += "Target Protein: " + proteinTarget + " per day\n\n";

        if (pref.equals("Veg")) {
            return header +
                    "Breakfast: Millet idli/oats porridge with nuts\n\n" +
                    "Lunch: Dal + 2 phulkas + spinach/bottle gourd\n" +
                    "       Curd (no sugar)\n\n" +
                    "Dinner: Vegetable soup + grilled tofu + 1 roti\n\n" +
                    "AVOID: White rice, sugar, fried meals\n\n" +
                    "Note: Adjust portions to match your " + proteinTarget + " target.";
        } else {
            return header +
                    "Breakfast: Boiled egg + multigrain toast\n\n" +
                    "Lunch: Grilled fish/chicken (100g)\n" +
                    "       Brown rice + greens\n\n" +
                    "Dinner: Egg omelet + salad + vegetable soup\n\n" +
                    "AVOID: Processed meats, white bread, sweet sauces\n\n" +
                    "Note: Adjust portions to match your " + proteinTarget + " target.";
        }
    }

    static String getNormalDiet(String goal, String pref, String proteinTarget) {
        String header = goal.toUpperCase() + " DIET (" + pref.toUpperCase() + ")\n";
        header += "Target Protein: " + proteinTarget + " per day\n\n";
        String dietPlan = "";

        if (goal.equals("Maintain Weight")) {
            if (pref.equals("Veg")) {
                dietPlan = header +
                        "Breakfast: Oats with milk & fruits OR 2 idlis + sambar\n" +
                        "          5 almonds + 2 walnuts\n\n" +
                        "Lunch: 2 phulkas + dal + mixed vegetables + salad\n" +
                        "       Brown rice (small portion)\n\n" +
                        "Dinner: Soup + grilled paneer/tofu + 1 chapati\n" +
                        "        Green tea";
            } else {
                dietPlan = header +
                        "Breakfast: 2 boiled eggs + multigrain toast\n" +
                        "          Fresh juice (no sugar)\n\n" +
                        "Lunch: Grilled chicken/fish + brown rice + veggies\n" +
                        "       Salad with lemon/olive oil\n\n" +
                        "Dinner: Chicken soup/egg curry + 1 roti + steamed vegetables";
            }
        } else if (goal.equals("Lose Weight")) {
            if (pref.equals("Veg")) {
                dietPlan = header +
                        "Breakfast: Warm water + lemon\n" +
                        "          Vegetable oats/poha + green tea\n\n" +
                        "Lunch: Brown rice/millets + moong dal + salad + curd\n\n" +
                        "Dinner: Clear soup + sautéed veggies + tofu/sprouts\n\n" +
                        "Snacks: Buttermilk, cucumber, roasted nuts (small)";
            } else {
                dietPlan = header +
                        "Breakfast: 2 boiled egg whites + green tea\n" +
                        "          1 slice whole grain bread\n\n" +
                        "Lunch: Grilled/boiled chicken/fish + steamed vegetables\n\n" +
                        "Dinner: Chicken/egg soup + small bowl rice";
            }
        } else { // Gain Weight
            if (pref.equals("Veg")) {
                dietPlan = header +
                        "Breakfast: Banana smoothie (milk, oats, almonds)\n" +
                        "          2 boiled potatoes OR toast with peanut butter\n\n" +
                        "Lunch: 2 phulkas + dal + paneer sabzi + rice + salad\n\n" +
                        "Dinner: Rice + rajma/dal + 1 glass milk\n\n" +
                        "Snacks: Dry fruits, protein shake, cheese sandwich";
            } else {
                dietPlan = header +
                        "Breakfast: 2 eggs + banana + milk shake\n\n" +
                        "Lunch: Chicken curry + rice + vegetables\n\n" +
                        "Dinner: Fish/chicken + brown rice/chapati + salad\n\n" +
                        "Snacks: Boiled eggs, nuts, high-calorie shakes";
            }
        }

        return dietPlan + "\n\nNote: Adjust portions to match your " + proteinTarget + " target.";
    }

    // Workout plan methods with type parameter
    static String getIronDeficiencyWorkout(String type) {
        if (type.equals("Simple Workout")) {
            return "IRON DEFICIENCY - SIMPLE WORKOUT (20 min/day)\n\n" +
                    "Warm-up (5 min):\n" +
                    "- Shoulder rolls - 30 sec\n" +
                    "- Neck rotations - 30 sec\n" +
                    "- Arm circles - 1 min\n" +
                    "- Marching in place - 2 min\n" +
                    "- Side bends - 1 min\n\n" +
                    "Main Routine (10 min):\n" +
                    "- Body-weight squats × 10\n" +
                    "- Wall push-ups × 8-10\n" +
                    "- Standing leg lifts × 10/leg\n" +
                    "- Seated knee extensions × 10\n" +
                    "- Standing calf raises × 15\n" +
                    "Repeat × 2 rounds\n\n" +
                    "Cool-down (5 min):\n" +
                    "- Deep breathing - 2 min\n" +
                    "- Light stretch";
        } else if (type.equals("Cardio")) {
            return "IRON DEFICIENCY - CARDIO (25-30 min, 4 days/week)\n\n" +
                    "Warm-up (5 min): Brisk walking or spot march\n\n" +
                    "Main Cardio (20 min) - Choose 1 or 2:\n" +
                    "- Brisk walking outdoors - 20 min\n" +
                    "- Stationary cycling - 15 min\n" +
                    "- Low-impact dance - 10 min\n" +
                    "- Skipping (slow pace) - 2 sets of 1 min\n\n" +
                    "Cool-down (5 min): Shoulder rolls and breathing\n\n" +
                    "Goal: Improve oxygen flow and iron utilization";
        } else { // Strength
            return "IRON DEFICIENCY - STRENGTH (3 days/week, 30 min)\n\n" +
                    "Warm-up (5 min):\n" +
                    "- Jumping jacks × 10\n" +
                    "- Arm swings × 20\n" +
                    "- Side leg kicks × 10/leg\n\n" +
                    "Main Routine (20 min):\n" +
                    "- Dumbbell bicep curls - 3 sets × 10\n" +
                    "- Wall/knee push-ups - 2 sets × 10\n" +
                    "- Squats - 3 sets × 10\n" +
                    "- Glute bridges - 2 sets × 15\n" +
                    "- Seated rows (resistance band) - 2 sets × 12\n" +
                    "- Plank - 20 sec hold\n\n" +
                    "Cool-down (5 min): Stretching";
        }
    }

    static String getHeartAttackWorkout(String type) {
        if (type.equals("Simple Workout")) {
            return "HEART RECOVERY - SIMPLE (20 min, 4 days/week)\n\n" +
                    "- March in place - 5 min\n" +
                    "- Seated leg lifts × 10\n" +
                    "- Calf raises × 15\n" +
                    "- Breathing exercises - 5 min\n\n" +
                    "*Stop if chest pain occurs*";
        } else if (type.equals("Cardio")) {
            return "HEART RECOVERY - CARDIO (25 min, 3 days/week)\n\n" +
                    "- Brisk walking/slow treadmill - 20 min\n" +
                    "- Cycling (slow pace) - 10 min\n" +
                    "- Relaxation yoga - 5 min\n\n" +
                    "*Doctor supervision recommended*";
        } else { // Strength
            return "HEART RECOVERY - STRENGTH (2 days/week, 20 min)\n\n" +
                    "- Wall push-ups × 10\n" +
                    "- Seated bicep curl (1 kg) × 10\n" +
                    "- Glute bridges × 12\n" +
                    "- Plank hold - 10 sec\n\n" +
                    "Goal: Stabilize heart, improve stamina";
        }
    }

    static String getDiabeticWorkout(String type) {
        if (type.equals("Simple Workout")) {
            return "DIABETES - NORMAL WORKOUT (20 min, 5 days/week)\n\n" +
                    "- Light stretch - 5 min\n" +
                    "- Brisk walking - 20 min\n" +
                    "- Simple squats × 10\n" +
                    "- Standing leg lifts × 10 each";
        } else if (type.equals("Cardio")) {
            return "DIABETES - CARDIO (30 min, 4 days/week)\n\n" +
                    "- Brisk walk/treadmill - 30 min\n" +
                    "- Cycling - 15 min\n" +
                    "- Dancing/aerobics - 10 min\n\n" +
                    "Goal: Lower blood sugar levels";
        } else { // Strength
            return "DIABETES - STRENGTH (3 days/week, 25 min)\n\n" +
                    "- Dumbbell squats × 10\n" +
                    "- Wall push-ups × 10\n" +
                    "- Arm curl (1-2 kg) × 10\n" +
                    "- Plank - 15 sec hold\n\n" +
                    "Track sugar before and after workout";
        }
    }

    static String getNormalWorkout(String goal, String type) {
        String workoutPlan = "";

        if (goal.equals("Maintain Weight")) {
            if (type.equals("Simple Workout")) {
                workoutPlan = "WEIGHT MAINTENANCE - SIMPLE (20 min)\n\n" +
                        "- Warm-up: Marching, shoulder rolls - 5 min\n" +
                        "- Body-weight squats × 12\n" +
                        "- Knee push-ups × 10";
            } else if (type.equals("Cardio")) {
                workoutPlan = "WEIGHT MAINTENANCE - CARDIO (30 min, 3-5 days/week)\n\n" +
                        "- Walking/jogging/cycling";
            } else {
                workoutPlan = "WEIGHT MAINTENANCE - STRENGTH (3 days/week)\n\n" +
                        "- Dumbbell curls, lunges, planks\n" +
                        "- Squats - 3 sets × 10 each";
            }
        } else if (goal.equals("Lose Weight")) {
            if (type.equals("Simple Workout")) {
                workoutPlan = "WEIGHT LOSS - SIMPLE (15 min daily)\n\n" +
                        "- Jumping jacks, high knees\n" +
                        "- Squats × 15, push-ups × 10, crunches × 15";
            } else if (type.equals("Cardio")) {
                workoutPlan = "WEIGHT LOSS - CARDIO (30-40 min, 5-6 days/week)\n\n" +
                        "- Brisk walk, jogging, cycling, aerobics";
            } else {
                workoutPlan = "WEIGHT LOSS - STRENGTH (3 days/week)\n\n" +
                        "- Resistance band training\n" +
                        "- Body-weight lunges, planks";
            }
        } else { // Gain Weight
            if (type.equals("Simple Workout")) {
                workoutPlan = "WEIGHT GAIN - MOBILITY (Daily)\n\n" +
                        "- Shoulder/neck rolls, light jogging - 5 min\n" +
                        "- Full-body stretch";
            } else if (type.equals("Cardio")) {
                workoutPlan = "WEIGHT GAIN - CARDIO (3 days/week, 20 min)\n\n" +
                        "- Light cycling/walking to stimulate appetite";
            } else {
                workoutPlan = "WEIGHT GAIN - STRENGTH (4 days/week, 30 min)\n\n" +
                        "- Squats, bench press, pull-ups, deadlift\n" +
                        "- Progressive overload for mass gain";
            }
        }

        return workoutPlan;
    }
}

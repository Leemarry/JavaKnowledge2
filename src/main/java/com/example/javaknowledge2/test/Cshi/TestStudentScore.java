package com.example.javaknowledge2.test.Cshi;

class StudentScore {
    private String studentName;
    private int score;
    // 静态变量，用来统计所有学生的总分
    static int totalScore = 0;
    // 静态变量，用来统计学生的总人数
    static int studentCount = 0;

    public StudentScore(String studentName, int score) {
        this.studentName = studentName;
        this.score = score;
        // 每次创建一个学生成绩对象，就更新总分和总人数
        totalScore += score;
        studentCount++;
    }

    public static int getAverageScore() {
        if (studentCount == 0) {
            return 0;
        }
        return totalScore / studentCount;
    }
}

class TestStudentScore {
    public static void main(String[] args) {
        StudentScore student1 = new StudentScore("Bob", 80);
        StudentScore student2 = new StudentScore("Carol", 90);

        // 不需要创建StudentScore类的实例，直接通过类名就可以访问静态变量和静态方法
        System.out.println("总分数: " + student1.totalScore);
        System.out.println("学生总人数: " + StudentScore.studentCount);
        System.out.println("平均分数: " + StudentScore.getAverageScore());

        StudentScore student3 = new StudentScore("David", 85);

        System.out.println("总分数: " + StudentScore.totalScore);
        System.out.println("学生总人数: " + StudentScore.studentCount);
        System.out.println("平均分数: " + StudentScore.getAverageScore());
    }
}
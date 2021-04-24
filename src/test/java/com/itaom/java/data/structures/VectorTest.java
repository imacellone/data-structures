package com.itaom.java.data.structures;

import com.italom.java.data.structures.Student;
import com.italom.java.data.structures.Vector;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VectorTest {

    @Test
    public void testVector() {
        Student s1 = new Student("John");
        Student s2 = new Student("Joseph");

        Vector<Student> vector = new Vector<>();
        vector.add(s1);
        vector.add(s2);
        System.out.println(vector);
        assertThat(vector.get(0)).isEqualTo(s1);
        assertThat(vector.get(1)).isEqualTo(s2);
    }

    @Test
    public void vectorShouldReturnCorrectNumberOfElements() {
        Student s1 = new Student("John");
        Student s2 = new Student("Joseph");


        Vector<Student> vector = new Vector<>();


        vector.add(s1);
        vector.add(s2);
        assertThat(vector.size()).isEqualTo(2);
    }

    @Test
    public void vectorShouldAnswerCorrectlyWhetherElementIsPresent() {
        Student s1 = new Student("John");
        Student s2 = new Student("Joseph");

        Vector<Student> vector = new Vector<>();

        vector.add(s1);
        assertThat(vector.contains(s1)).isTrue();
        assertThat(vector.contains(s2)).isFalse();
    }

    @Test
    public void vectorShouldThrowExceptionWhenAccessingInvalidIndex() {
        Vector<Student> vector = new Vector<>();
        Student s1 = new Student("John");
        vector.add(s1);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            vector.get(1);
        });
    }

    @Test
    public void vectorShouldReturnCorrectElementWhenIndexIsValid() {
        Vector<Student> vector = new Vector<>();
        Student s1 = new Student("John");
        Student s2 = new Student("Joseph");
        vector.add(s1);
        vector.add(s2);
        Student actual = vector.get(1);
        assertThat(actual).isEqualTo(s2);
    }

    @Test
    public void vectorShouldBeAbleToInsertAnElementAtValidIndex() {
        Vector<Student> vector = new Vector<>();
        Student s1 = new Student("John");
        Student s2 = new Student("Joseph");
        Student s3 = new Student("Alice");
        vector.add(s1);
        vector.add(s2);
        vector.add(1, s3);

        assertThat(vector.size()).isEqualTo(3);
        assertThat(vector.get(0)).isEqualTo(s1);
        assertThat(vector.get(1)).isEqualTo(s3);
        assertThat(vector.get(2)).isEqualTo(s2);
    }

    @Test
    public void vectorShouldThrowExceptionWhenInsertingAtInvalidPosition() {
        Vector<Student> vector = new Vector<>();
        vector.add(0, new Student("John"));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            vector.add(2, new Student("Alice"));
        });
    }

    @Test
    public void vectorShouldBeAbleToRemoveAnElementFromValidIndex() {
        Vector<Student> vector = new Vector<>();


        Student s1 = new Student("John");
        Student s2 = new Student("Joseph");
        Student s3 = new Student("Alice");

        vector.add(s1);
        vector.add(s2);
        vector.add(s3);

        assertThat(vector.size()).isEqualTo(3);
        assertThat(vector.get(0)).isEqualTo(s1);
        assertThat(vector.get(1)).isEqualTo(s2);
        assertThat(vector.get(2)).isEqualTo(s3);

        vector.remove(1);

        assertThat(vector.size()).isEqualTo(2);
        assertThat(vector.get(0)).isEqualTo(s1);
        assertThat(vector.get(1)).isEqualTo(s3);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            vector.get(2);
        });
    }

    @Test
    public void vectorShouldThrowExceptionWhenDeletingAtInvalidPosition() {

        Vector<Student> vector = new Vector<>();

        vector.add(0, new Student("John"));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            vector.remove(1);
        });
    }

    @Test
    public void vectorShouldExpandWhenNeeded() {
        Vector<Student> vector = new Vector<>();
        for (int i = 0; i < 101; i++)
            vector.add(new Student(""));
        assertThat(vector.size()).isEqualTo(101);
    }

    @Test
    public void vectorShouldContractWhenNeeded() {
        Vector<Student> vector = new Vector<>();
        for (int i = 0; i < 200; i++)
            vector.add(new Student(""));
        for (int i = 199; i >= 1; i--)
            vector.remove(i);
        assertThat(vector.size()).isEqualTo(1);
    }
}

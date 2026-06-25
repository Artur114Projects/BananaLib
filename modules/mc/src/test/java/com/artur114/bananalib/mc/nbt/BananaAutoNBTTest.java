package com.artur114.bananalib.mc.nbt;

import com.artur114.bananalib.mc.nbt.auto.AutoNBTContainer;
import net.minecraft.nbt.NBTTagCompound;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BananaAutoNBTTest {
    @Test
    void testAll() {
        Person person = new Person();
        person.age = 20;
        person.name = "Анатолий";
        person.h = 1_000_000;

        System.out.println(person);
        NBTTagCompound data = BananaAutoNBT.writeToNBT(person, new NBTTagCompound());
        System.out.println(data);

        Person person1 = new Person();
        BananaAutoNBT.readFromNBT(person1, data);
        System.out.println(person1);
    }

    @AutoNBTContainer
    public static class Person {
        private long id = System.nanoTime();
        private String name;
        private int age;
        private int h;

        @Override
        public String toString() {
            return name + ", " + age + ", " + h + ", " + id;
        }
    }
}
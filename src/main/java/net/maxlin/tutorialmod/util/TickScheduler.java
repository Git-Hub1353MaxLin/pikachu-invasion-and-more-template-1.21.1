package net.maxlin.tutorialmod.util;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TickScheduler {

    private static final List<ScheduledTask> tasks = new ArrayList<>();

    public static void schedule(Runnable action, int delayTicks) {
        tasks.add(new ScheduledTask(action, delayTicks));
    }

    public static void tick() {
        Iterator<ScheduledTask> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            ScheduledTask task = iterator.next();
            task.delay--;
            if (task.delay <= 0) {
                task.action.run();
                iterator.remove();
            }
        }
    }

    public static void schedule(ServerWorld serverWorld, int delayTicks, Object o) {

    }

    private static class ScheduledTask {
        Runnable action;
        int delay;

        public ScheduledTask(Runnable action, int delay) {
            this.action = action;
            this.delay = delay;
        }
    }
}

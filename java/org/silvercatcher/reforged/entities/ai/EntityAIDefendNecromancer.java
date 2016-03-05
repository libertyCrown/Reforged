package org.silvercatcher.reforged.entities.ai;

import java.util.Objects;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;

public class EntityAIDefendNecromancer extends EntityAIBase {

	
	protected final EntityPlayer master;
	protected final EntityCreature slave;
		
	protected final int updateInterval;
	protected final double attackSpeed;
	
	protected EntityLivingBase target;
	protected int updateCountdown;
	
	
	public EntityAIDefendNecromancer(EntityPlayer master, EntityCreature slave) {
	
		this(master, slave, 15, 1.3);
	}

	public EntityAIDefendNecromancer(EntityPlayer master, EntityCreature slave,
			int updateInterval, double attackSpeed) {
		
		setMutexBits(3);
		
		this.master = Objects.requireNonNull(master, "Master must be defined!");
		this.slave = Objects.requireNonNull(slave, "Slave must be defined!");
		
		this.updateInterval = Math.max(0, updateInterval);
		this.attackSpeed = Math.max(0, attackSpeed);
	}

	@Override
	public boolean shouldExecute() {
		
		return target != null && target.isEntityAlive() && slave.canEntityBeSeen(target);
	}
	
	
	@Override
	public void startExecuting() {
		
		slave.setAttackTarget(target);
		slave.setRevengeTarget(target);
		
		PathNavigate navigator = slave.getNavigator();
		
		if(navigator.noPath() && updateCountdown-- == 0) {
			
			updateCountdown = updateInterval;
			navigator.tryMoveToEntityLiving(target, attackSpeed);
			System.out.println(navigator.getPath());
		}
	}
	
	
	public void setTarget(EntityLivingBase target) {
		
		if(target == master || target == slave) {
			target = null;
		}
		this.target = target;
	}
}
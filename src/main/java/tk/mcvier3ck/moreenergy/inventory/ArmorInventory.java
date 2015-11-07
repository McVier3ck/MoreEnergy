package tk.mcvier3ck.moreenergy.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import tk.mcvier3ck.moreenergy.container.ArmorContainer;
import tk.mcvier3ck.moreenergy.item.ArmorUpgrade;
import tk.mcvier3ck.moreenergy.item.ArmorUpgradeLevel;

public class ArmorInventory implements IInventory{
	
	ItemStack armor;
	EntityPlayer player;
	public ItemStack[] slots = new ItemStack[4];
	private ArmorContainer container;
	
	public ArmorInventory(EntityPlayer player) {
		this.armor = player.inventory.getCurrentItem();
		this.player = player;
	}
	
	public void setContainer(ArmorContainer container) {
		this.container = container;
	}


	@Override
	public int getSizeInventory() {
		
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		
		return slots[slot];
	} 
	
	

	@Override
	public ItemStack decrStackSize(int slot, int count) {
		if (this.slots[slot] != null) {
			ItemStack itemstack;

			if (this.slots[slot].stackSize <= count) {
				itemstack = slots[slot];
				this.slots[slot] = null;
				return itemstack;
			} else {
				itemstack = this.slots[slot].splitStack(count);

				if (this.slots[slot].stackSize == 0) {
					this.slots[slot] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}
	}
	
	@Override
	public void markDirty() {	
		
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack item = getStackInSlot(i);
		if (item != null)
			setInventorySlotContents(i, null);
		return item;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		if(isItemValidForSlot(slot, itemStack)) {
			slots[slot] = itemStack;
			if (itemStack.stackSize > getInventoryStackLimit()) {
				itemStack.stackSize = getInventoryStackLimit();
			}
		} else {
			player.inventory.addItemStackToInventory(itemStack);
		}
	}
	
	@Override
	public String getInventoryName() {
		
		return armor.getDisplayName();
	}

	@Override
	public boolean hasCustomInventoryName() {
		
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory() {
		
	}

	@Override
	public void closeInventory() {	
	}	

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
		if(slot ==0 && itemStack != null && itemStack.getItem() instanceof ArmorUpgradeLevel) {
			return true;
		} else if(slot >=1 && itemStack != null && itemStack.getItem() instanceof ArmorUpgrade) {
			return true;
		}
		return false;
	}

	
	
}
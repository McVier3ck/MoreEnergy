package tk.mcvier3ck.moreenergy.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import tk.mcvier3ck.moreenergy.inventory.ArmorInventory;
import tk.mcvier3ck.moreenergy.item.ItemRegistry;
import tk.mcvier3ck.moreenergy.util.InventoryHelper;

public class ArmorContainer extends Container {

	public ArmorInventory armor;
	private EntityPlayer player;
	private ItemStack stack;
	public int level =0;
	
	public ArmorContainer(InventoryPlayer inv) {
		this.player = inv.player;
		this.armor = new ArmorInventory(player);
		
		this.stack = player.getHeldItem();
		this.armor.setContainer(this);
		loadInventory(armor.slots);
		
		addSlotToContainer(new Slot(armor, 0, 8, 8));
		addSlotToContainer(new Slot(armor, 1, 44, 19));
		addSlotToContainer(new Slot(armor, 2, 80, 19));
		addSlotToContainer(new Slot(armor, 3, 116, 19));
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inv, j + i * 9 + 9, 8 + (j * 18), 44 + (i * 18)));
			}
		}
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inv, i, 8 + (18 * i), 102));
		}

	}



	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);
		// null checks and checks if the item can be stacked (maxStackSize > 1)
		if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();

			
			if (slot < armor.getSizeInventory()) {
				if (!this.mergeItemStack(stackInSlot, armor.getSizeInventory(), 36 + armor.getSizeInventory(),
						true)) {
					return null;
				}
			}

			
			else if (!this.mergeItemStack(stackInSlot, 1, armor.getSizeInventory(), false)) {
				return null;
			}

			if (stackInSlot.stackSize == 0) {
				slotObject.putStack(null);
			} else {
				slotObject.onSlotChanged();
			}

			if (stackInSlot.stackSize == stack.stackSize) {
				return null;
			}
			slotObject.onPickupFromSlot(player, stackInSlot);
		}
		return stack;
	}
	
	

	@Override
	public boolean canInteractWith(EntityPlayer player) {

		return armor.isUseableByPlayer(player);
	}
	
	public void saveInventory(ItemStack[] inventory){
		NBTTagCompound nbt = InventoryHelper.readInventory(inventory);
		InventoryHelper.writeNBT(stack, nbt);
		player.setCurrentItemOrArmor(0, stack);
	}
	
	public void loadInventory(ItemStack[] inventory){
		NBTTagCompound nbt = InventoryHelper.readNBT(stack);
		ItemStack[] newInventor = InventoryHelper.writeInventory(inventory, nbt);
		for(int i=0; i<newInventor.length; i++){
			armor.setInventorySlotContents(i, newInventor[i]);
		}
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		saveInventory(armor.slots);
		
		if(armor.slots[0] !=null){
			Item item = armor.slots[0].getItem();
			if(item == ItemRegistry.ArmorUpgrade1) level=1;
			if(item == ItemRegistry.ArmorUpgrade2) level=2;
			if(item == ItemRegistry.ArmorUpgrade3) level=3; 
		} else {
			level = 0;
		}
	}
	

}
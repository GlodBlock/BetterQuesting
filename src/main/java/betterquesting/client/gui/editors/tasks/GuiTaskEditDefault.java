package betterquesting.client.gui.editors.tasks;

import net.minecraft.client.gui.GuiScreen;
import betterquesting.api.client.gui.GuiScreenThemed;
import betterquesting.api.enums.EnumSaveType;
import betterquesting.api.questing.tasks.ITask;
import betterquesting.client.gui.editors.json.scrolling.GuiJsonEditor;
import com.google.gson.JsonObject;

public class GuiTaskEditDefault extends GuiScreenThemed
{
	public final ITask task;
	public final JsonObject json;
	public boolean isDone = false;
	
	public GuiTaskEditDefault(GuiScreen parent, ITask task)
	{
		super(parent, task.getUnlocalisedName());
		this.task = task;
		this.json = task.writeToJson(new JsonObject(), EnumSaveType.CONFIG);
		this.isDone = false;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		
		if(!isDone)
		{
			this.isDone = true;
			this.mc.displayGuiScreen(new GuiJsonEditor(this, json, task.getDocumentation()));
		} else
		{
			this.task.readFromJson(json, EnumSaveType.CONFIG);
			this.mc.displayGuiScreen(parent);
		}
	}
}
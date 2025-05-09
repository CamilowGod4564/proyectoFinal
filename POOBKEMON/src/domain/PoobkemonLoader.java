package domain;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PoobkemonLoader {
	
	
	public HashMap<String,Type> loadTypes(String path) throws ClassNotFoundException, FileNotFoundException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		HashMap<String,Type> types = new HashMap<>();
		Class<?> clase = Class.forName("domain.Type");
		Constructor<?> constructor = clase.getConstructors()[0];
        
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			String newTypeName = null;
			ArrayList<String> ar1 = new ArrayList<>();
			ArrayList<String> ar2 = new ArrayList<>();
			ArrayList<String> ar3 = new ArrayList<>();
			int cont = 0;
			
			while((line = br.readLine()) != null) {
				line = line.strip();
	            if (line.isEmpty()) continue;
	            if(!line.startsWith("*")) {
	            	if(line.startsWith("-")) {
	            		cont += 1 ;
	            	}
	            	if(line.startsWith("=")) {
	            		line = line.substring(1).strip();
	            		switch(cont) {
	            			case 1:
	            				ar1.add(line);
	            				break;
	            			case 2:
	            				ar2.add(line);
	            				break;
	            			case 3:
	            				ar3.add(line);
	            				break;
	            		}
	            	}
	            	if(cont == 3) {
	            		cont = 0;
	            		Type type = (Type) constructor.newInstance(new Object[]{newTypeName,ar1,ar2,ar3});
	                    types.put(type.getTypeName(), type);
	                    newTypeName = null;
	                    ar1.clear();
	                    ar2.clear();
	                    ar3.clear();
	            	}
	            }else {
	            	line = line.substring(1).strip();
	            	newTypeName = line;
	            }
			}
		}
		return types;
	}
	
	public HashMap<String,Status> loadStatuses(String path){
		HashMap<String,Status> statuses = new HashMap<>();
		Map<String, Class<? extends Status>> classType = new HashMap<>();
		classType.put("INDEFINITEONGOINGDAMAGE", IndefiniteOnGoingDamage.class);
		classType.put("INDEFINITEACTIONBLOCKING", IndefiniteActionBlocking.class);
		classType.put("FINITEACTIONBLOCKING", FiniteActionBlocking.class);
		classType.put("FINITEDURATIONSTATUS", FiniteDurationStatus.class);
		classType.put("HEALTHBASEDDAMAGE", HealthBasedDamage.class);
		classType.put("SELFHARMACTIONBLOCKING", SelfHarmActionBlocking.class);
		        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String statusType = fields[0].toUpperCase();
                Class<? extends Status> clase = classType.get(statusType);

                if (clase == null) {
                    throw new IllegalArgumentException("Tipo desconocido: " + statusType); //cambiar esta exception
                }
                Constructor<?> constructor = clase.getConstructors()[0];
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Object[] parameters = new Object[parameterTypes.length];
                for (int i = 0; i < parameterTypes.length; i++) {
                    parameters[i] = convertirValor(fields[i + 1], parameterTypes[i]);
                }

                Status s = (Status) constructor.newInstance(parameters);
                statuses.put(s.getName(),s);
            }
        } catch (Exception e) {
           //excepcion
        }

        return statuses;
	}
	public HashMap<String,Movement> loadMovements( HashMap<String,Type> types,HashMap<String,Status> statuses,String path){
		HashMap<String,Movement> movements = new HashMap<>();
		Map<String, Class<? extends Movement>> classType = new HashMap<>();
		classType.put("SPECIALSTATUS", SpecialStatus.class);
		classType.put("SPECIAL", Special.class);
		classType.put("PHYSICAL", Physical.class);
		classType.put("DIRECTDAMAGE", DirectDamage.class);
		//classType.put("STATUSMOVEMENT", StatusMovement.class); falta codigo de eso
		        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String movementType = fields[0].toUpperCase();
                Class<? extends Movement> clase = classType.get(movementType);

                if (clase == null) {
                    throw new IllegalArgumentException("Tipo desconocido: " + movementType); //cambiar esta exception
                }
                Constructor<?> constructor = clase.getConstructors()[0];
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Object[] parameters = new Object[parameterTypes.length];
                for (int i = 0; i < parameterTypes.length; i++) {
                	if(parameterTypes[i] == Type.class) {
                		parameters[i] = types.get(fields[i + 1]);
                	}else if(parameterTypes[i] == Status.class){
                		parameters[i] = statuses.get(fields[i + 1]);
                	}else {
                    parameters[i] = convertirValor(fields[i + 1], parameterTypes[i]);
                	}
                }
                Movement m = (Movement) constructor.newInstance(parameters);
                movements.put(m.getName().toUpperCase(),m);
            }
        } catch (Exception e) {
           //excepcion
        }

        return movements;
	}
	public TreeMap<String,Pokemon> loadPokemons(String path,Map<String,Movement> movements,HashMap<String,Type> types) {
		Pattern bracketsPattern = Pattern.compile("\\[(.*?)\\]");
		Pattern bracesPattern = Pattern.compile("\\{(.*?)\\}");
		
	    TreeMap<String,Pokemon> pokemons = new TreeMap<>();
	    Class<Pokemon> pokemonClass = Pokemon.class;
	    Constructor<?> constructor = pokemonClass.getConstructors()[0];
	    Class<?>[] paramTypes = constructor.getParameterTypes();
	    ArrayList<Type> pokemonTypes = new ArrayList<>();
	 
	    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
	    	String line;
	        while ((line = br.readLine()) != null) {
	            String[] fields = line.split(",", paramTypes.length);
	            Object[] parameters = new Object[paramTypes.length];
	            for (int i = 0; i < paramTypes.length-1; i++) {
	                parameters[i] = convertirValor(fields[i].trim(),paramTypes[i]);
	            }
	            Matcher bracketsMatcher = bracketsPattern.matcher(fields[paramTypes.length-1]);
	            while (bracketsMatcher.find()) {
	                String contentInsideBrackets = bracketsMatcher.group(1);
	                String [] pokemonNameTypes = contentInsideBrackets.split(",");
	                for(String s: pokemonNameTypes) {
	                	pokemonTypes.add(types.get(s));
	                }
	            }
	            parameters[paramTypes.length-1] = pokemonTypes;
	            Pokemon p = (Pokemon) constructor.newInstance(parameters);
	            Matcher bracesMatcher = bracesPattern.matcher(fields[paramTypes.length-1]);
	            while (bracesMatcher.find()) {
	                String contentInsideBraces = bracesMatcher.group(1);
	                String[] moveNames = contentInsideBraces.split(",");          
	                for (String m : moveNames) {
	                    p.addMovement(movements.get(m.trim().toUpperCase()));
	                }
	            }
	            pokemons.put(p.getName(), p);
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Error cargando pokémons", e);
	    }
	    return pokemons;
	}
	
	public HashMap<String,Item> loadItems(String path){
		HashMap<String,Item> items = new HashMap<>();
		Map<String, Class<? extends Item>> classType = new HashMap<>();
		classType.put("POTION", Potion.class);
		classType.put("REVIVE", Revive.class);
		        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String itemsType = fields[0].toUpperCase();
                Class<? extends Item> clase = classType.get(itemsType);

                if (clase == null) {
                    throw new IllegalArgumentException("Tipo desconocido: " + itemsType); //cambiar esta exception
                }
                Constructor<?> constructor = clase.getConstructors()[0];
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Object[] parameters = new Object[parameterTypes.length];
                for (int i = 0; i < parameterTypes.length; i++) {
                    parameters[i] = convertirValor(fields[i + 1], parameterTypes[i]);
                }

                Item i = (Item) constructor.newInstance(parameters);
                items.put(i.getName(),i);
            }
        } catch (Exception e) {
           //excepcion
        }

        return items;
	}
	
	
	
	private static Object convertirValor(String valor, Class<?> tipoDestino) {
	    if (tipoDestino == String.class) {
	        return valor;
	    } else if (tipoDestino == int.class || tipoDestino == Integer.class) {
	        return Integer.parseInt(valor);
	    } else if (tipoDestino == double.class || tipoDestino == Double.class) {
	        return Double.parseDouble(valor);
	    }else if (tipoDestino == boolean.class || tipoDestino == Boolean.class) {
	        return Boolean.parseBoolean(valor);
	    }
	    throw new IllegalArgumentException("Tipo no soportado: " + tipoDestino);
	}
}
	
